package com.xzxy.mqtt.consumer;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mateam.mqtt.connector.data.MqttPacket;

@Component
public class MqttMessageProcessor {

	Logger logger = LoggerFactory.getLogger(MqttMessageProcessor.class);
	
	//消费总线服务的
	@Autowired
    RestTemplate restTemplate;

	public void process(MqttPacket packet) {
		String topic = packet.getTopic();
		logger.info("Packet arrived from topic: {}", topic);
		String msg = "";
		if (topic.startsWith("lastwill/")) {
			msg = topic.split("/")[1];
			logger.info("LAST WILL received from {}", topic.split("/")[1]);
		} else {
			String message = new String(packet.getMqttMessage().getPayload(),
					Charset.forName("UTF-8"));
			msg = message;
			logger.info("Message received: {}", message);
		}
		//向日志服务模块写入日志，
		restTemplate.getForEntity("http://log-service/logInfo?msg="+msg, String.class).getBody();
	}
}