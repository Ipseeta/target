package com.myRetail.exception;
/**
 * Generic exception class
 * @author ipseeta
 *
 */
public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GenericException(String exceptionMsg){
		super(exceptionMsg);
	}

}
