package com.mateam.mqtt.connector.connection;

import com.google.common.eventbus.EventBus;
import com.mateam.mqtt.connector.data.MqttPacket;
import com.mateam.mqtt.connector.events.MqttPacketAvailableEvent;
import com.mateam.mqtt.connector.exceptions.CantSendException;
import com.mateam.mqtt.connector.exceptions.ConnectionException;
import com.mateam.mqtt.connector.util.SslUtil;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * Manage connections for MQTT brokers.
 *
 * @author danielbrutti
 *
 */
@Component
public class MqttConnector implements MqttCallback {

    Logger logger = LoggerFactory.getLogger(MqttConnector.class);

    protected EventBus eventBus = new EventBus();

    private MqttClient mqttClient;
    private MqttConnectOptions mqttConnOptions;

    private String brokerUrl;
    private String clientId;
    private String lastWillTopic;
    private String brokerUserName = null;
    private String brokerPassword = null;
    private String filePersistenceDirectory = null;
    private boolean useSecureConnection;

    /**
     * Set connection parameters.
     *
     * @param brokerURL URL of the broker server
     * @param userName User to connect broker server when secure connection is being used.
     * @param password Password to connect broker server when secure connection is being used.
     * @param clientId Identifier of current client into MQTT broker.
     * @param useSecureConnection enable/disable secure connections.
     */
    public void initiliaze(String brokerURL, String userName, String password, String lastWillTopic, String clientId,
                           boolean useSecureConnection, String filePersistenceDirectory) {

        logger.info("Initializing connection to " + brokerURL);

        this.brokerUrl = brokerURL;
        this.brokerUserName = userName;
        this.brokerPassword = password;
        this.clientId = clientId;
        this.lastWillTopic = lastWillTopic;
        this.useSecureConnection = useSecureConnection;
        this.filePersistenceDirectory = filePersistenceDirectory;
    }


    /**
     * Connects to MQTT server.
     */
    public void connect() throws ConnectionException {

        // setup MQTT Client
        mqttConnOptions = new MqttConnectOptions();

        mqttConnOptions.setCleanSession(true);

        byte[] lastWillMessage = "DISCONNECTED".getBytes();
        mqttConnOptions.setWill(this.lastWillTopic, lastWillMessage, 1, true);

        mqttConnOptions.setKeepAliveInterval(60);

        if (useSecureConnection) {
            try {
                mqttConnOptions.setSocketFactory(SslUtil.getSocketFactory("m2mqtt_ca.crt", "TLSv1.1"));
            } catch (Exception e) {
                logger.error("Can not load SSL certificate", e);
            }
        }
        if (brokerUserName != null && brokerPassword != null) {
            mqttConnOptions.setUserName(brokerUserName);
            mqttConnOptions.setPassword(brokerPassword.toCharArray());
        }

        // Connect to Broker
        try {
            if(filePersistenceDirectory != null) {
                MqttDefaultFilePersistence mqttDefaultFilePersistence = new MqttDefaultFilePersistence(filePersistenceDirectory);
                mqttClient = new MqttClient(brokerUrl, clientId, mqttDefaultFilePersistence);
            } else {
                mqttClient = new MqttClient(brokerUrl, clientId);
            }
            mqttClient.setCallback(this);
            mqttClient.connect(mqttConnOptions);
        } catch (MqttException e) {
            throw new ConnectionException("Error trying to open MQTT Broker connection", e);
        }

        logger.info("Connected to " + brokerUrl);

    }

    /**
     * Check if connection with MQTT broker is open.
     */
    public boolean isConnected() {
        return mqttClient != null && mqttClient.isConnected();
    }

    /**
     * Disconnect from MQTT server.
     */
    public void disconnect() throws ConnectionException {
        // disconnect
        try {
            mqttClient.disconnect();
        } catch (Exception e) {
            throw new ConnectionException("Error trying to close MQTT Broker connection", e);
        }
    }

    /**
     * Publish a message into MQTT broker.
     */
    public void send(MqttPacket mqttPacket) throws CantSendException {
        // get message
        MqttMessage message = mqttPacket.getMqttMessage();

        // get topic
        String topicName = mqttPacket.getTopic();

        MqttTopic topic = mqttClient.getTopic(topicName);

        if (topic != null) {

            // subscribe(topicName, 0);

            MqttDeliveryToken token = null;

            try {
                logger.debug("Publish on server {} and topic {}", mqttClient.getServerURI(),
                        topic.getName());

                // publish message to broker
                token = topic.publish(message);

                // Wait until the message has been delivered to the broker
                token.waitForCompletion(1000);

            } catch (MqttException e) {
                logger.error("Error on MQTT publish.", e);

                processException(e.getReasonCode());

                throw new CantSendException("Error on MQTT publish.", e);
            }
        } else {
            logger.error("Topic does not exists.");
            throw new CantSendException("Topic does not exists.");
        }
    }

    private void processException(int reasonCode) {
        switch (reasonCode) {
            case MqttException.REASON_CODE_CLIENT_NOT_CONNECTED:
            case MqttException.REASON_CODE_CLIENT_ALREADY_DISCONNECTED:
            case MqttException.REASON_CODE_CONNECTION_LOST:
            case MqttException.REASON_CODE_SERVER_CONNECT_ERROR:
            case MqttException.REASON_CODE_CLIENT_CLOSED:
            case MqttException.REASON_CODE_CLIENT_TIMEOUT:
            case MqttException.REASON_CODE_UNEXPECTED_ERROR:
            case MqttException.REASON_CODE_WRITE_TIMEOUT:
                this.reconnect();
                break;

            default:
                logger.warn("MqttException error not supported yet. Error Code: {}, please check MqttException class documentation.", reasonCode);
        }
    }

    /**
     * Subscribes to a MQTT topic for current connection topics on m2m.io are in the form <domain>/
     * <stuff>/<thing>
     *
     * @param topicName name to subscribe to.
     * @param subQoS QoS level, could be 0, 1 or 2.
     * @return
     */
    public void subscribe(String topicName, int subQoS) {

        if (mqttClient == null) {
            logger.error("Unable to subscribe, connection is closed.");
            return;
        }

        // subscribe to topic and add it to the list
        try {
            mqttClient.subscribe(topicName, subQoS);
        } catch (MqttException e) {
            processException(e.getReasonCode());
            logger.error("Error on MQTT subscribe.", e);
        }

    }

    /**
     * Unsubscribes to a MQTT topic for current connection topics on m2m.io are in the form <domain>/
     * <stuff>/<thing>
     *
     * @param topicName name to unsubscribe from.
     * @return
     */
    public void unsubscribe(String topicName) {

        if (mqttClient == null) {
            logger.error("Unable to unsubscribe, connection is closed.");
            return;
        }

        // unsubscribe to topic and add it to the list
        try {
            mqttClient.unsubscribe(topicName);
        } catch (MqttException e) {
            processException(e.getReasonCode());
            logger.error("Error on MQTT subscribe.", e);
        }

    }

    /**
     * Close and open the connection to MQTT broker.
     */
    public void reconnect() {
        try {
            disconnect();
        } catch (ConnectionException e) {
            logger.warn("Cannot disconnect or already disconnected.", e);
        }
        try {
            connect();
        } catch (ConnectionException e) {
            logger.warn("Cannot recconenct or already connected.", e);
        }
    }

    /**
     *
     * connectionLost This callback is invoked upon losing the MQTT connection.
     *
     */
    @Override
    public void connectionLost(Throwable t) {
        logger.info("MQTT Connection lost!", t);
        reconnect();
    }


    /**
     *
     * deliveryComplete This callback is invoked when a message published by this client is
     * successfully received by the broker.
     *
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        logger.info("Publish complete on MQTT Topic");
    }

    /**
     *
     * messageArrived This callback is invoked when a message is received on a subscribed topic.
     *
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        logger.debug("Message arrived on Topic {}", topic);
        logger.debug("Message: {}", message.getPayload());

        MqttPacket mqttPacket = new MqttPacket(topic, message);
        MqttPacketAvailableEvent event = new MqttPacketAvailableEvent(mqttPacket);
        eventBus.post(event);

    }

    public void registerListener(Object listener){
        eventBus.register(listener);
    }

    public void unregisterListener(Object listener){
        try{
            eventBus.unregister(listener);
        } catch (Exception e){
            //Nothing to do, it throws exception if listener is not registered
        }
    }


}
