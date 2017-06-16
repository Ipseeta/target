package com.myRetail.exception;
/**
 * Caters product searching exceptions
 * @author ipseeta
 *
 */
public class ProductSearchException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	 
	public ProductSearchException(String exceptionMsg){
		super(exceptionMsg);
	}
}
