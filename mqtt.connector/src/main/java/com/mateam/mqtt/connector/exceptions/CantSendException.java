package com.mateam.mqtt.connector.exceptions;

public class CantSendException extends Throwable {

	private static final long serialVersionUID = 1L;

	public CantSendException(){
		super();
	}
	
	public CantSendException(String message){
		super(message);
	}
	
	public CantSendException(String message, Throwable e){
		super(message, e);
	}
}