package com.myRetail.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * Price POJO class
 * @author ipseeta
 *
 */
@XmlRootElement
public class Price implements Serializable{

	private static final long serialVersionUID = 1L;
	private int _id;
	private double value;
	private String currency_code;
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	@Override
	public String toString() {
		return "Price [value=" + value + ", currency_code=" + currency_code + "]";
	}

}
