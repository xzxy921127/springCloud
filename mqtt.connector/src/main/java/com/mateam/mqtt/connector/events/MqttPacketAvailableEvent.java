package com.mateam.mqtt.connector.events;

import com.mateam.mqtt.connector.data.MqttPacket;

public class MqttPacketAvailableEvent {

	private MqttPacket mqttPacket;

	public MqttPacketAvailableEvent(MqttPacket mqttPacket){
		this.mqttPacket = mqttPacket;
	}

	public MqttPacket getMqttPacket() {
		return mqttPacket;
	}

	public void setMqttPacket(MqttPacket mqttPacket) {
		this.mqttPacket = mqttPacket;
	}
}
