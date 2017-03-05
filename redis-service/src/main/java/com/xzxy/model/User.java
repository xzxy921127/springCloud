package com.xzxy.model;

/**
 * 用户类<br/>
 * 写入redis中的类要实现Serializable接口
 * @author XZXY
 *
 */
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private String password;
	
	private Integer age;
	
	public User() {
	}

	public User(String name, String password, Integer age) {
		this.name = name;
		this.password = password;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}