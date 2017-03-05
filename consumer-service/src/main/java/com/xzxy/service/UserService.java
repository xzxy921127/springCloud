package com.xzxy.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 调用用户管理服务的代理类
 * @author XZXY
 *
 */
@Service
public class UserService {
	
	@Autowired
	RestTemplate restTemplate;
	
	/**
	 * 添加用户服务<br/>
	 * @HystrixCommand 注解的作用是当服务调用失败时执行的方法
	 * @param name 用户名
	 * @param age 年龄
	 * @param password 密码
	 * @return
	 */
	@HystrixCommand(fallbackMethod="serviceFailback")
	public String addUserService(String name,String age,String password){
		String url = "http://USER-SERVICE/add?name="+name+"&age="+age+"&password="+password;
//		String url = "http://USER-SERVICE/add";
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name", name);
		param.put("age", age);
		param.put("password", password);
		return restTemplate.getForEntity(url, String.class).getBody();
	}
	
	/**
	 * 删除用户服务
	 * @param id 主键ID
	 * @return
	 */
	@HystrixCommand(fallbackMethod="serviceFailback")
	public String deleteUserService(Long id){
		String url = "http://USER-SERVICE/delete?id="+id;
		return restTemplate.getForEntity(url, String.class).getBody();
	}
	
	/**
	 * 更新用户服务
	 * @param name 用户名
	 * @param age 年龄
	 * @param id 主键ID
	 * @return
	 */
//	@HystrixCommand(fallbackMethod="serviceFailback")
	public String updateUserService(String name, Integer age, Long id){
		String url = "http://USER-SERVICE/update?name="+name+"&age="+age+"&id="+id;
//		String url = "http://USER-SERVICE/update";
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name", name);
		param.put("age", age);
		return restTemplate.getForEntity(url, String.class).getBody();
	}
	
	/**
	 * 查询用户列表服务
	 * @return
	 */
	@HystrixCommand(fallbackMethod="serviceFailback")
	public String findAllUserService(){
		String url = "http://USER-SERVICE/findAll";
		return restTemplate.getForEntity(url, String.class).getBody();
	}
	
	/**
	 * 通过用户名获取用户的服务
	 * @param username 用户名
	 * @return
	 */
	@HystrixCommand(fallbackMethod="serviceFailback")
	public String findUserByName(String username){
		String url = "http://USER-SERVICE/findUserByName?name="+username;
		return restTemplate.getForEntity(url, String.class).getBody();
	}
	
	/**
	 * 通过主键ID获取用户的服务
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod="serviceFailback")
	public String getUserById(Long id) {
		String url = "http://USER-SERVICE/getUserById?id="+id;
		return restTemplate.getForEntity(url, String.class).getBody();
	}
	
	/**
	 * 分页获取用户信息的服务
	 * @param pageNo 页码
	 * @param name 用户名
	 * @param age 年龄
	 * @return
	 */
	@HystrixCommand(fallbackMethod="serviceFailback")
	public String getUserListByPageService(String pageNo, String name,
			String age) {
		String url = "http://USER-SERVICE/findAllByPage?pageNo="+pageNo+"&name="+name+"&age="+age;
		return restTemplate.getForEntity(url, String.class).getBody();
	}
	
	public String serviceFailback(){
		return "error";
	}
	
	public String serviceFailback(String name){
		String msg = "调用UserService.findUserByName()方法失败! name:"+name;
		System.err.println(msg);
		return "error";
	}
	
	public String serviceFailback(String name,String age){
		String msg = "调用UserService.addUserService(String name, String age)方法失败! name:"+name +" age:"+age;
		System.err.println(msg);
		return "error";
	}
	
	public String serviceFailback(String pageNo,String name,String age){
		String msg = "调用UserService.getUserListByPageService()方法失败! pageNo:"+pageNo
				+" name:"+name+" age:"+age;
		System.err.println(msg);
		return "error";
	}
	
	public String serviceFailback(Long id){
		String msg = "调用UserService.deleteUserService()方法失败! id:"+id;
		System.err.println(msg);
		return "error";
	}
}
