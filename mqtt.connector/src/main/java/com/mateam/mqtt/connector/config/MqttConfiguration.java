package com.mateam.mqtt.connector.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration file.
 * 
 * @author danielbrutti
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.mateam.mqtt" })
public class MqttConfiguration {

}
