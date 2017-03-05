package com.mateam.mqtt.connector.data;


import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by danielbrutti on 06/06/16.
 */
public class MqttPacket {
    private String topic;
    private MqttMessage mqttMessage;

    public MqttPacket(String topic, MqttMessage mqttMessage) {
        this.topic = topic;
        this.mqttMessage = mqttMessage;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public MqttMessage getMqttMessage() {
        return mqttMessage;
    }

    public void setMqttMessage(MqttMessage mqttMessage) {
        this.mqttMessage = mqttMessage;
    }
}
