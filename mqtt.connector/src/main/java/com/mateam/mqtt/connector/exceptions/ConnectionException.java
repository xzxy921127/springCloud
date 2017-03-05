package com.mateam.mqtt.connector.exceptions;

public class ConnectionException extends Throwable {

	private static final long serialVersionUID = 1L;

	public ConnectionException(){
		super();
	}
	
	public ConnectionException(String message){
		super(message);
	}
	
	public ConnectionException(String message, Throwable e){
		super(message, e);
	}
}
