package com.kserver.util;

public class KException extends Exception {

	private static final long serialVersionUID = 1L;

	public KException() {
		super();
	}

	public KException(String message) {
		super(message);
	}

	public KException(String message, Throwable cause) throws NoSuchMethodError {
		super(message, cause);
	}
}
