package com.xzxy.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import redis.clients.jedis.JedisPoolConfig;

/**
 * 使用redis管理session<br/>
 * 目前只支持单机版
 * @author XZXY
 *
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {
	
	/*
    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration(),jedisPoolConfig());
        
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setTimeout(2000);
        return jedisConnectionFactory; 
    }
    */
    /**
     * redis集群配置
     * 
     * 配置redis集群的结点及其它一些属性
     * 
     * @return
    private RedisClusterConfiguration redisClusterConfiguration(){
        RedisClusterConfiguration redisClusterConfig = new RedisClusterConfiguration();

        redisClusterConfig.setClusterNodes(getClusterNodes());
        
        redisClusterConfig.setMaxRedirects(3);
        return redisClusterConfig;
        
    }
    */
    
    /**
     * JedisPoolConfig 配置
     * 
     * 配置JedisPoolConfig的各项属性
     * 
     * @return
     */
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig= new JedisPoolConfig();
        //连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted(true);
        
        //是否启用pool的jmx管理功能, 默认true
        jedisPoolConfig.setJmxEnabled(true);
        
        //默认就好
        //jedisPoolConfig.setJmxNamePrefix("pool");
        
        //jedis调用returnObject方法时，是否进行有效检查
        jedisPoolConfig.setTestOnReturn(true);
        
        //是否启用后进先出, 默认true
        jedisPoolConfig.setLifo(true);
        
        //最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(8);
        
        //最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(8);
        
        //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(-1);
        
        //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        jedisPoolConfig.setMinEvictableIdleTimeMillis(1800000);
        
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(0);
        
        //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        jedisPoolConfig.setNumTestsPerEvictionRun(3);
        
        //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)   
        jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(1800000);
        
        //在获取连接的时候检查有效性, 默认false
        jedisPoolConfig.setTestOnBorrow(false);
        
        //在空闲时检查有效性, 默认false
        jedisPoolConfig.setTestWhileIdle(false);
        
        //逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(-1);
        
        return jedisPoolConfig;
    }
    
    /**
     * redis集群节点IP和端口的添加
     * 
     * 节点：RedisNode redisNode = new RedisNode("127.0.0.1",6379);
     * 
     * @return
     */
    public Set<RedisNode> getClusterNodes(){
        // 添加redis集群的节点
        Set<RedisNode> clusterNodes = new HashSet<RedisNode>();
        // 这三个主节点是我本机的IP和端口,从节点没有加入 ，这里不是我真实的IP，虽然是内网，还是不要太直接了
        clusterNodes.add(new RedisNode("127.0.0.1", 7000));
        clusterNodes.add(new RedisNode("127.0.0.1", 7001));
        clusterNodes.add(new RedisNode("127.0.0.1", 7002));
        return clusterNodes;
    }
}
