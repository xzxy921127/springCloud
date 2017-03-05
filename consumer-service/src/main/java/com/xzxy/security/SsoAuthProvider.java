package com.xzxy.security;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xzxy.service.SecurityUserService;

/**
 * Spring Security验证器
 * @author XZXY
 *
 */
@Component
public class SsoAuthProvider implements AuthenticationProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(SsoAuthProvider.class);
	
	@Autowired
    private SecurityUserService securityUserService;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        request.getSession().removeAttribute("loginMsg");
        
		String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails user = securityUserService.loadUserByUsername(username);
		String securityUsername = user.getUsername();
		if(SecurityUserService.UN_EXIST_USER.equals(securityUsername)){
			request.getSession().setAttribute("loginMsg", "用户不存在:"+username);
			throw new BadCredentialsException("用户不存在.");
		}
        
        LOGGER.info("password={}, needPassword={}", password, user.getPassword());
        //密码匹配验证
        if (passwordEncoder().matches(password, user.getPassword())) {
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            return new UsernamePasswordAuthenticationToken(user, password, authorities);
        }
        String msg = "{\"reply\":\"success\", \"name\" : \""+username+"\"}";
        request.getSession().setAttribute("loginMsg", msg);
		return authentication;
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}
