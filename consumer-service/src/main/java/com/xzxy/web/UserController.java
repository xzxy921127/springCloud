package com.xzxy.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzxy.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("list")
	public String list(HttpServletRequest request){
		request.setAttribute("pageNo", "1");
		request.setAttribute("totalPage", "2");
		request.getSession().setAttribute("sessionmsg", "12345678");
		return "user/list";
	}
	
	@ResponseBody
	@RequestMapping(value="getUserList",method={RequestMethod.POST,RequestMethod.GET})
	public String getUserList(){
		String content = userService.findAllUserService();
		return content;
	}
	
	@ResponseBody
	@RequestMapping(value="getUserListByPage")
	public String getUserListByPage(@RequestParam("pageNo") String pageNo,@RequestParam("name") String name,@RequestParam("age") String age){
		String content = userService.getUserListByPageService(pageNo,name,age);
		return content;
	}
	
	@ResponseBody
	@RequestMapping("add")
	public String add(@RequestParam("name") String name, @RequestParam("age") String age, @RequestParam("password") String password){
		String content = userService.addUserService(name, age,password);
		return content;
	}
	
	@RequestMapping("edit")
	public String edit(){
		return "user/edit";
	}
	
	@ResponseBody
	@RequestMapping("update")
	public String update(@RequestParam("name") String name, @RequestParam("age") Integer age,@RequestParam("id") Long id){
		String content = userService.updateUserService(name, age, id);
		return content;
	}
	
	@ResponseBody
	@RequestMapping("delete")
	public String delete(@RequestParam Long id){
		String content = userService.deleteUserService(id);
		return content;
	}
	
	@ResponseBody
	@RequestMapping("getUserById")
	public String getUserById(@RequestParam Long id){
		String content = userService.getUserById(id);
		return content;
	}
}