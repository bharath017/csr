package com.csr.exception;


public class TicketNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 183743L;
	public TicketNotFoundException(String message) {
		super(message);
	}
	
	
}
