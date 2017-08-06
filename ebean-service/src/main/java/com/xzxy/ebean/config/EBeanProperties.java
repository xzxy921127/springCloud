package com.xzxy.ebean.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * ebean配置类
 * @author XZXY
 *
 */
@Component
@ConfigurationProperties(locations = "classpath:application.properties", prefix = "ebean.db")
public class EBeanProperties {

	/** 用户名 */
	private String username;

	/** 密码 */
	private String password;

	/** 数据库连接 */
	private String databaseUrl;

	/** 数据库驱动类 */
	private String databaseDriver;
	
	/** 分区数 */
	private Integer partitionCount;
	
	/** 每个分区最大连接数 */
	private Integer maxConnectionsPerPartition;
	
	/** 每个分区最小连接数 */
	private Integer minConnectionsPerPartition;
	
	/** 连接的最大空闲时间(分钟为单位)<br/>不要设置一个很大的值 */
	private Long idleMaxAgeInMinutes;
	
	/** 测试connection的间隔时间(分钟为单位)<br/>和idleMaxAgeInMinutes搭配使用，不要设置一个很大的值 */
	private Long idleConnectionTestPeriod;
	
	/** 连接测试语句 */
	private String connectionTestStatement;
	
	/** ebean加载包 */
	private String packages;
	
	/** 自动提交模式 */
	private Boolean autoCommitMode;

	/** 连接池在无空闲连接可用时一次性创建的新数据库连接数 */
	private Integer acquireIncrement;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDatabaseUrl() {
		return databaseUrl;
	}

	public void setDatabaseUrl(String databaseUrl) {
		this.databaseUrl = databaseUrl;
	}

	public String getDatabaseDriver() {
		return databaseDriver;
	}

	public void setDatabaseDriver(String databaseDriver) {
		this.databaseDriver = databaseDriver;
	}

	public Integer getPartitionCount() {
		if(null==partitionCount){
			return 2;
		}
		return partitionCount;
	}

	public void setPartitionCount(Integer partitionCount) {
		this.partitionCount = partitionCount;
	}

	public Integer getMaxConnectionsPerPartition() {
		if(null==maxConnectionsPerPartition){
			return 20;
		}
		return maxConnectionsPerPartition;
	}

	public void setMaxConnectionsPerPartition(Integer maxConnectionsPerPartition) {
		this.maxConnectionsPerPartition = maxConnectionsPerPartition;
	}

	public Integer getMinConnectionsPerPartition() {
		if(null == minConnectionsPerPartition){
			return 10;
		}
		return minConnectionsPerPartition;
	}

	public void setMinConnectionsPerPartition(Integer minConnectionsPerPartition) {
		this.minConnectionsPerPartition = minConnectionsPerPartition;
	}

	public Long getIdleMaxAgeInMinutes() {
		if(null==idleMaxAgeInMinutes){
			return 60L;
			
		}
		return idleMaxAgeInMinutes;
	}

	public void setIdleMaxAgeInMinutes(Long idleMaxAgeInMinutes) {
		this.idleMaxAgeInMinutes = idleMaxAgeInMinutes;
	}

	public Long getIdleConnectionTestPeriod() {
		if(null == idleConnectionTestPeriod){
			return 5L;
		}
		return idleConnectionTestPeriod;
	}

	public void setIdleConnectionTestPeriod(Long idleConnectionTestPeriod) {
		this.idleConnectionTestPeriod = idleConnectionTestPeriod;
	}

	public String getConnectionTestStatement() {
		if(null == connectionTestStatement){
			return "select 1";
		}
		return connectionTestStatement;
	}

	public void setConnectionTestStatement(String connectionTestStatement) {
		this.connectionTestStatement = connectionTestStatement;
	}

	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	public Boolean getAutoCommitMode() {
		if(null == autoCommitMode){
			return false;
		}
		return autoCommitMode;
	}

	public void setAutoCommitMode(Boolean autoCommitMode) {
		this.autoCommitMode = autoCommitMode;
	}

	public Integer getAcquireIncrement() {
		if(null == acquireIncrement){
			return 2;
		}
		return acquireIncrement;
	}

	public void setAcquireIncrement(Integer acquireIncrement) {
		this.acquireIncrement = acquireIncrement;
	}
}
