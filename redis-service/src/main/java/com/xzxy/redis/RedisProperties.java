package com.xzxy.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * redis配置信息类
 * @author XZXY
 *
 */
@Component
@ConfigurationProperties(prefix = "redis.cache")
public class RedisProperties {
	
	/** 节点信息 */
	private String clusterNodes;
	
	/** 命令超时时间 */
	private int commandTimeout;
	
	/** 数据缓存时间 */
	private int expireSeconds;

	public String getClusterNodes() {
		return clusterNodes;
	}

	public void setClusterNodes(String clusterNodes) {
		this.clusterNodes = clusterNodes;
	}

	public int getCommandTimeout() {
		return commandTimeout;
	}

	public void setCommandTimeout(int commandTimeout) {
		this.commandTimeout = commandTimeout;
	}

	public int getExpireSeconds() {
		return expireSeconds;
	}

	public void setExpireSeconds(int expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
}
