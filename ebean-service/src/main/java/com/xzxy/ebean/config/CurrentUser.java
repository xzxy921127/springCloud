package com.xzxy.ebean.config;

import org.springframework.stereotype.Component;

import com.avaje.ebean.config.CurrentUserProvider;

@Component
public class CurrentUser implements CurrentUserProvider {

	public Object currentUser() {
		return "db";
	}

}
