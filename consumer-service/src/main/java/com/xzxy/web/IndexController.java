package com.xzxy.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xzxy.service.UserService;

/**
 * 首页和测试用的Controller
 * @author XZXY
 *
 */
@Controller
public class IndexController {
	
	@Value("${application.message:Hello World}")
	private String message = "Hello World";
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
    public String login(ModelMap model) {
		String userContent = userService.findUserByName("xzxy");
System.err.println("获取用户数据:"+userContent);		
        return "login";
    }
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@RequestMapping("/index")
	public String index(Map<String, Object> model) {
		return "index";
	}
	
	/*@ResponseBody
	@RequestMapping("/findAll")
	public String findAll(){
		String data = userService.findAllUserService();
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public String add(String name,String age,String password){
		String data = userService.addUserService(name, age,password);
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(Long id){
		String data = userService.deleteUserService(id);
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public String update(Long id,String name,Integer age){
		String data = userService.updateUserService(name, age, id);
		return data;
	}
*/	
	@RequestMapping(value = "/first", method = RequestMethod.GET)  
    public Map<String, Object> firstResp (HttpServletRequest request){  
        Map<String, Object> map = new HashMap<>();  
        request.getSession().setAttribute("request Url", request.getRequestURL());
        map.put("request Url", request.getRequestURL());  
        return map;  
    }
  
    @RequestMapping(value = "/sessions", method = RequestMethod.GET)  
    public String sessions (HttpServletRequest request,HttpServletResponse response){  
        String sessionId = request.getSession().getId();
        String requestUrl = request.getRequestURL().toString();
        Cookie[] cookies = request.getCookies();
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("requestUrl", requestUrl);
        request.setAttribute("cookies", cookies);
        return "info";
    }
}