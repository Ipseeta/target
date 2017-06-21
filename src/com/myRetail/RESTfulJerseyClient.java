package com.myRetail;

import javax.ws.rs.core.MediaType;

import com.myRetail.model.Price;
import com.myRetail.model.Product;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
/**
 * Test class
 * @author ipseeta
 *
 */
public class RESTfulJerseyClient {

	private static final String webServiceURI = "http://localhost:8080/myRetail-0.0.1-SNAPSHOT/myRetail/products/";

	public static void main(String[] args) {
		Client client = Client.create();
		WebResource webResource = client.resource(webServiceURI);
		createDummyPriceTest(webResource);
		getTest(webResource);
		getByIdTest(webResource);
		getNameByIdTest(webResource);
		updatePriceTest(webResource);
		deleteTest(webResource);
		
	}
	
	private static void getTest(WebResource webResource) {
		ClientResponse response = webResource.get(ClientResponse.class);
		if (200 != response.getStatus())
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		String output = response.getEntity(String.class);
		System.out.println(output);
		
	}

	private static void createDummyPriceTest(WebResource webResource){
		Price p = new Price();
		p.set_id(16483589);
		p.setCurrency_code("USD");
		p.setValue(13.49);
		//String input = "{\"currency_code\":\"USD\",\"_id\":\"16483589\",\"value\":\"13.49\"}";
		ClientResponse response = webResource.path("create").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,p);
		if (201 != response.getStatus())
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		String output = response.getEntity(String.class);
		System.out.println(output);
	}
	
	private static void getByIdTest(WebResource webResource){
		ClientResponse response = webResource.path("16483589").get(ClientResponse.class);
		/*if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}*/
		String output = response.getEntity(String.class);
		System.out.println(output);
	}
	
	private static void getNameByIdTest(WebResource webResource){
		ClientResponse response = webResource.path("name").path("16483589").get(ClientResponse.class);
		if (200 != response.getStatus())
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		String output = response.getEntity(String.class);
		System.out.println(output);
	}
	
	private static void updatePriceTest(WebResource webResource){
		Product product = new Product();
		product.setId(16483589);
		product.setName("Dazed and Confused");
		Price price = new Price();
		price.set_id(16483589);
		price.setCurrency_code("INR");
		price.setValue(300.67);
		product.setCurrent_price(price);
		ClientResponse response = webResource.path("16483589").accept(MediaType.APPLICATION_JSON).put(ClientResponse.class,product);
		if (200 != response.getStatus())
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		String output = response.getEntity(String.class);
		System.out.println(output);
	}
	
	private static void deleteTest(WebResource webResource){
		
		ClientResponse response = webResource.path("16483589").delete(ClientResponse.class);
		if (202 == response.getStatus()) {
			String output = response.getEntity(String.class);
			System.out.println(output);
		} else throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
	}
}
