package com.xzxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 这是redis集群服务的启动类
 * @author XZXY
 *
 */
//@EnableDiscoveryClient
@SpringBootApplication
public class RedisApplication {
	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}
}