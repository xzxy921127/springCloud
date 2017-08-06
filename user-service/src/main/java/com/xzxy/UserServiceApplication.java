package com.xzxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * 启动用户管理的服务
 * @author XZXY
 *
 */
//@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceApplication {
	public static void main(String[] args) {
//		new SpringApplicationBuilder(UserServiceApplication.class).web(true).run(args);
		SpringApplication app = new SpringApplication(UserServiceApplication.class);
		app.setBannerMode(Mode.OFF);
	    app.run(args);
	}
}
