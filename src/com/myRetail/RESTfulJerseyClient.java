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
		getByIdTest(webResource);
		getNameByIdTest(webResource);
		updatePriceTest(webResource);
		
	}
	
	private static void createDummyPriceTest(WebResource webResource){
		ClientResponse response = webResource.path("create").get(ClientResponse.class);
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
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
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
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		String output = response.getEntity(String.class);
		System.out.println(output);
	}
}
