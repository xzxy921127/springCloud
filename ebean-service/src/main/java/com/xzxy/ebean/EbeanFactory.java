package com.xzxy.ebean;

import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.PropertyMap;
import com.avaje.ebean.config.ServerConfig;
import com.jolbox.bonecp.BoneCPDataSource;
import com.xzxy.ebean.config.CurrentUser;
import com.xzxy.ebean.config.EBeanProperties;

@Component
public class EbeanFactory implements FactoryBean<EbeanServer> {

	@Autowired
	private CurrentUser currentUser;
	
	@Autowired
	private EBeanProperties ebeanProperties;

	public EbeanServer getObject() throws Exception {
	    ServerConfig config = new ServerConfig();
	    config.setName("db");
	    config.setCurrentUserProvider(currentUser);
//	    config.setExternalTransactionManager(new SpringAwareJdbcTransactionManager());
	    BoneCPDataSource dataSource = new BoneCPDataSource();
	    dataSource.setUsername(ebeanProperties.getUsername());
	    dataSource.setPassword(ebeanProperties.getPassword());
	    dataSource.setJdbcUrl(ebeanProperties.getDatabaseUrl());
	    dataSource.setDriverClass(ebeanProperties.getDatabaseDriver());
	    dataSource.setPartitionCount(ebeanProperties.getPartitionCount());
	    dataSource.setMaxConnectionsPerPartition(ebeanProperties.getMaxConnectionsPerPartition());
	    dataSource.setMinConnectionsPerPartition(ebeanProperties.getMinConnectionsPerPartition());
	    dataSource.setIdleConnectionTestPeriodInMinutes(ebeanProperties.getIdleConnectionTestPeriod());
	    dataSource.setConnectionTestStatement(ebeanProperties.getConnectionTestStatement());
	    dataSource.setIdleMaxAgeInMinutes(ebeanProperties.getIdleMaxAgeInMinutes());
	    dataSource.setAcquireIncrement(ebeanProperties.getAcquireIncrement());
	    config.setDataSource(dataSource);
	    
	    config.loadFromProperties();
	    
	    // set as default and register so that Model can be
	    // used if desired for save() and update() etc
	    config.setDefaultServer(true);
	    config.setRegister(true);
//	    Properties p = PropertyMap.defaultProperties();
//	    String packages = ebeanProperties.getPackages();
//	    System.out.println("ebean packages:"+packages);
//        p.put("packages", ebeanProperties.getPackages());
//        config.loadFromProperties(p);
        config.setAutoCommitMode(ebeanProperties.getAutoCommitMode());
	    return EbeanServerFactory.create(config);
	}

	@Override
	public Class<?> getObjectType() {
		return EbeanServer.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
