package com.xzxy.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@ComponentScan("com.xzxy")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	protected final Logger LOGGER = LoggerFactory
			.getLogger(WebSecurityConfig.class);

	// 自定义验证
	@Autowired
	private SsoAuthProvider ssoAuthProvider;

	@Autowired
	@Qualifier("securityUserService")
	UserDetailsService userDetailsService;

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 设置不拦截的静态资源
		web.ignoring().antMatchers("/ui/**");
	}

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.authenticationProvider(ssoAuthProvider);
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.sessionManagement().sessionFixation().migrateSession();
		http.sessionManagement().invalidSessionUrl("/index");
		//允许所有用户访问登录界面
		http.authorizeRequests().antMatchers("/login").permitAll().anyRequest()
		//允许表单登录
		.authenticated().and().formLogin().loginPage("/login")
		.permitAll().and().logout().logoutUrl("/logout")
		//注销时删除session
		.deleteCookies("JSESSIONID").logoutSuccessUrl("/login");
		/*
		 *http.authorizeRequests().antMatchers("/login").permitAll().anyRequest()
				.authenticated().and().formLogin().loginPage("/login")
				.permitAll().and().logout().logoutUrl("/logout")
				.deleteCookies("JSESSIONID").logoutSuccessUrl("/login");
		 * 
		 * 
		 * http.authorizeRequests() .antMatchers("/", "/home").permitAll()
		 * .antMatchers("/admin/**").access("hasRole('ADMIN')")
		 * .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
		 * .and().formLogin().loginPage("/login")
		 * .usernameParameter("ssoId").passwordParameter("password")
		 * .and().rememberMe
		 * ().rememberMeParameter("remember-me").tokenRepository
		 * (persistentTokenRepository()).tokenValiditySeconds(86400)
		 * .and().csrf()
		 * .and().exceptionHandling().accessDeniedPage("/Access_Denied");
		 */
	}
	
}
