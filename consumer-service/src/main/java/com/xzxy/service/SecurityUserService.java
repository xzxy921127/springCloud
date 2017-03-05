package com.xzxy.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xzxy.model.User;

@Service("securityUserService")
public class SecurityUserService implements UserDetailsService {
	
	protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	public static final String UN_EXIST_USER = "_undefined_";
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		String content = userService.findUserByName(username);
		content = content.replaceAll("\\\\\"", "");
		content.replaceAll("\"\\[", "[");
		content.replaceAll("]\"", "]");
		String msg = "通过远程调用获取用户信息:"+content;
		LOGGER.equals(msg);
System.err.println("通过远程调用获取用户信息:"+msg);
		User user = new User();
		user.setName("Lily");
		user.setPassword("123456");
		JSONObject json = JSONObject.fromObject(content);
		String userContent = json.get("user").toString();
		if(StringUtils.isBlank(userContent)){
//			throw new UserNotExistException("用户不存在!");
			return new org.springframework.security.core.userdetails.User(UN_EXIST_USER, UN_EXIST_USER, 
					 true, true, true, true, getGrantedAuthorities(user));
			
		}
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), 
			 true, true, true, true, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		authorities.add(new SimpleGrantedAuthority("ROLE_DBA"));
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}
}
