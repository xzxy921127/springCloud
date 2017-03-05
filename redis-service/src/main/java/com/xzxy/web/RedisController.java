package com.xzxy.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xzxy.common.MyConstants;
import com.xzxy.model.User;
import com.xzxy.redis.MyRedisTemplate;

/**
 * 公布给外界访问的redis集群服务的API
 * @author XZXY
 *
 */
@Controller
@RequestMapping
public class RedisController {

	@Autowired
	private MyRedisTemplate myRedisTemplate;

	/**
	 * 测试redis的方法
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping("testJedisCluster")
    public User testJedisCluster(@RequestParam("username") String username){
        String value =  myRedisTemplate.get(MyConstants.USER_FORWARD_CACHE_PREFIX, username);
        if(StringUtils.isBlank(value)){
            myRedisTemplate.set(MyConstants.USER_FORWARD_CACHE_PREFIX, username, JSON.toJSONString(new User("xzxy","123456",19)));
            return null;
        }
        return JSON.parseObject(value, User.class);
    }
	
	/**
	 * 设置缓存数据
	 * @param key
	 * @param value
	 * @return
	 */
	@ResponseBody
	@RequestMapping("set")
    public String set(@RequestParam("key") String key, @RequestParam("value")String value){
        String oldValue =  myRedisTemplate.get(MyConstants.USER_FORWARD_CACHE_PREFIX, key);
        if(StringUtils.isBlank(oldValue)){
        	String json = "{\"reply\":\"success\", \"data\":"+value+" }";
            myRedisTemplate.set(MyConstants.USER_FORWARD_CACHE_PREFIX, key, value);
            return json;
        }
        return "{\"reply\":\"fail\"}";
    }
	
	/**
	 * 获取缓存数据
	 * @param key
	 * @param value
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get")
    public String get(@RequestParam("key") String key){
		String value =  myRedisTemplate.get(MyConstants.USER_FORWARD_CACHE_PREFIX, key);
        if(StringUtils.isNotBlank(value)){
        	String data = myRedisTemplate.get(MyConstants.USER_FORWARD_CACHE_PREFIX, key);
        	String json = "{\"reply\":\"success\", \"data\":"+data+" }";
            return json;
        }
        return "{\"reply\":\"fail\"}";
    }
}
