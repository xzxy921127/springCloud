package com.xzxy.web;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xzxy.model.User;
import com.xzxy.model.UserRepository;

/**
 * 公布给外界访问的用户服务的API
 * @author XZXY
 *
 */
@RestController
public class UserController {

	private final Logger logger = Logger.getLogger(getClass());

	private static final String SUCCESS_JSON = "{\"reply\":\"success\"}";

	private static final String ERROR_JSON = "{\"reply\":\"error\"}";

	@Autowired
	private DiscoveryClient client;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/hello")
	public String hello() {
		return "Hello World";
	}

	/**
	 * 添加用户
	 * @param name 用户名
	 * @param age 年龄
	 * @param password 密码
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add(@RequestParam String name,@RequestParam Integer age,@RequestParam String password) {
		User o = new User();
		o.setName(name);
		o.setAge(age);
		o.setPassword(password);
		userRepository.save(o);
		return SUCCESS_JSON;
	}
	
	/**
	 * 删除用户
	 * @param id 主键ID
	 * @return
	 */
	@RequestMapping(value = "/delete")
    public String delete(@RequestParam Long id) {
		 userRepository.delete(id);
		 return SUCCESS_JSON;
    }

	/**
	 * 更新用户
	 * @param name 用户名
	 * @param age 年龄
	 * @param id 主键ID
	 * @return
	 */
	@RequestMapping(value = "/update")
    public String update(@RequestParam String name,@RequestParam Integer age, @RequestParam Long id) {
		userRepository.update(name, age, id);
		return SUCCESS_JSON;
    }
	
	/**
	 * 通过用户名获取用户
	 * @param name 用户名
	 * @return
	 */
	@RequestMapping(value = "/findUserByName")
    public String findUserByName(@RequestParam String name) {
		User user = userRepository.findUserByName(name);
		JSONObject json = new JSONObject();
		try {
			json.put("reply", "success");
			if(user != null){
				String userJson = net.sf.json.JSONObject.fromObject(user).toString();
				json.put("user", userJson);
			}else{
				json.put("user", "");
			}
		} catch (JSONException e) {
			e.printStackTrace();
			logger.debug("调用接口失败");
			return ERROR_JSON;
		}
		return json.toString();
    }
	
	/**
	 * 分页查询用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findAllByPage")
    public String findAllByPage(HttpServletRequest request) {
		//用户名
		String name = request.getParameter("name");
		//年龄
		String age = request.getParameter("age");
		//页码
		String pageNoStr = request.getParameter("pageNo");
		int pageNo = 1;
		if(StringUtils.isNotBlank(pageNoStr)){
			pageNo = Integer.parseInt(pageNoStr);
		}
		if(pageNo<1){
			pageNo = 1;
		}
		int pageSize = 10;
		Pageable pageable = new PageRequest(pageNo-1, pageSize);
		Specification<User> spec = getWhereClause(name,age);
		Page<User> pageUser = userRepository.findAll(spec, pageable);
		int totalPage = pageUser.getTotalPages();
		List<User> list = pageUser.getContent();
		JSONObject json = new JSONObject();
		try {
			json.put("reply", "success");
			json.put("pageNo", pageNo);
			json.put("totalPage", totalPage);
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
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findAll")
    public String findAll(HttpServletRequest request) {
		//默认查询前10条
		Pageable pageable = new PageRequest(0, 10);
		Page<User> pageUser = userRepository.findAll(pageable);
		List<User> list = pageUser.getContent();
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
	 * @param id 主键ID
	 * @return
	 */
	@RequestMapping(value = "/getUserById")
	public String getUserById(@RequestParam Long id){
		User user = userRepository.getUserById(id);
		JSONObject json = new JSONObject();
		try {
			json.put("reply", "success");
			if(user != null){
				String userJson = net.sf.json.JSONObject.fromObject(user).toString();
				json.put("user", userJson);
			}else{
				json.put("user", "");
			}
		} catch (JSONException e) {
			e.printStackTrace();
			logger.debug("调用接口失败");
			return ERROR_JSON;
		}
		return json.toString();
	}
	
	private Specification<User> getWhereClause(String name,String age) {
		  return new Specification<User>() {
		    @Override
		    public Predicate toPredicate(Root<User> r, CriteriaQuery<?> q, CriteriaBuilder cb) {
		    Predicate predicate = cb.conjunction();
		       if (StringUtils.isNotBlank(name)) {
		         predicate.getExpressions().add(
		           cb.like(r.<String>get("name"), "%" + StringUtils.trim(name) + "%")
		         );
		       }
		       if(StringUtils.isNotBlank(age)){
		    	   int userAge = Integer.parseInt(age);
		    	   predicate.getExpressions().add(
				           cb.equal(r.<Integer>get("age"), userAge)
				         );
		       }
		       return predicate;
		    }
		  };
		}
}