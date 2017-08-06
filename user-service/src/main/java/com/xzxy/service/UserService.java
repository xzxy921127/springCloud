package com.xzxy.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import com.xzxy.form.UserForm;
import com.xzxy.model.User;

@Service
public class UserService {
	
	public List<User> list(UserForm form){
		ExpressionList<User> expr = Ebean.find(User.class).where();
		String name = form.getName();
		if(StringUtils.isNotBlank(name)){
			expr.like("name", "%"+name+"%");
		}
		Integer age = form.getAge();
		if(null != age){
			expr.eq("age", age);
		}
		return expr.findList();
	}
	
	public boolean save(UserForm form){
		User user = new User();
		BeanUtils.copyProperties(form, user);
		user.save();
		return true;
	}
	
	public boolean update(UserForm form){
		User user = Ebean.find(User.class,form.getId());
		BeanUtils.copyProperties(form, user, new String[]{"id"});
		user.update();
		return true;
	}
	
	public boolean delete(UserForm form){
		User user = Ebean.find(User.class,form.getId());
		user.delete();
		return true;
	}
}
