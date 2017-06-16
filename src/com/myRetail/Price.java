package com.myRetail;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Price {
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

}
