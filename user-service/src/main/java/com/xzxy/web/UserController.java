package com.xzxy.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.xzxy.ebean.EbeanFactory;
import com.xzxy.form.UserForm;
import com.xzxy.model.User;
import com.xzxy.service.UserService;

/**
 * 公布给外界访问的用户服务的API
 * 
 * @author XZXY
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	private final Logger logger = Logger.getLogger(getClass());

	private static final String SUCCESS_JSON = "{\"reply\":\"success\"}";

	private static final String ERROR_JSON = "{\"reply\":\"error\"}";

	// @Autowired
	// private DiscoveryClient client;

	@Autowired
	private UserService userService;

	@Autowired
	private EbeanServer ebeanServer;

	/**
	 * 添加用户
	 * @return
	 */
	@RequestMapping("/add")
	public String add(@RequestBody UserForm form) {
		this.userService.save(form);
		return SUCCESS_JSON;
	}

	/**
	 * 删除用户
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(@RequestBody UserForm form) {
		this.userService.delete(form);
		return SUCCESS_JSON;
	}

	/**
	 * 更新用户
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(@RequestBody UserForm form) {
		this.userService.update(form);
		return SUCCESS_JSON;
	}

	/**
	 * 通过用户名获取用户
	 * @return
	 */
	@RequestMapping(value = "/findUserByName")
	public String findUserByName(@RequestBody UserForm form) {
		return SUCCESS_JSON;
	}

	/**
	 * 分页查询用户列表
	 * @return
	 */
	@RequestMapping(value = "/search")
	public String search(@RequestBody UserForm form) {
		System.out.println("ebeanServer:" + ebeanServer);
		List<User> list = this.userService.list(form);
		JSONObject json = new JSONObject();
		try {
			json.put("reply", "success");
			json.put("pageNo", 1);
			json.put("totalPage", 1);
			String userlistJson = net.sf.json.JSONArray.fromObject(list).toString();
			json.put("list", userlistJson);
			return json.toString();
		} catch (JSONException e) {
			e.printStackTrace();
			logger.debug("调用接口失败");
			return ERROR_JSON;
		}
	}

	/**
	 * 查询所有用户信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findAll")
	public String findAll(@RequestBody UserForm form) {
		// 默认查询前10条
		List<User> list = this.userService.list(form);
		JSONObject json = new JSONObject();
		try {
			json.put("reply", "success");
			String userlistJson = net.sf.json.JSONArray.fromObject(list).toString();
			json.put("list", userlistJson);
			return json.toString();
		} catch (JSONException e) {
			e.printStackTrace();
			logger.debug("调用接口失败");
			return ERROR_JSON;
		}
	}

	/**
	 * 通过用户ID获取用户信息
	 * 
	 * @param id
	 *            主键ID
	 * @return
	 */
	@RequestMapping(value = "/getUserById")
	public String getUserById(@RequestBody UserForm form) {
		User user = Ebean.find(User.class, form.getId());
		JSONObject json = new JSONObject();
		try {
			json.put("reply", "success");
			if (user != null) {
				String userJson = net.sf.json.JSONObject.fromObject(user).toString();
				json.put("user", userJson);
			} else {
				json.put("user", "");
			}
		} catch (JSONException e) {
			e.printStackTrace();
			logger.debug("调用接口失败");
			return ERROR_JSON;
		}
		return json.toString();
	}
}