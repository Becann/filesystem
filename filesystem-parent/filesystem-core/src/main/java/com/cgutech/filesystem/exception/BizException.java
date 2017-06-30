package com.cgutech.filesystem.exception;

public class BizException extends RuntimeException {

	private static final long serialVersionUID = 2015211929584359183L;
	
	public BizException() {
		super();
	}
	
	public BizException(String message) {
		super(message);
	}

}
