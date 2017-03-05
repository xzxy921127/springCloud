package com.xzxy.exception;

public class UserNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserNotExistException(){
		super("User is Not Exist!");
	}
	
	public UserNotExistException(String message){
		super(message);
	}
}
