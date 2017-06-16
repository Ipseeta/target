package com.myRetail;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.myRetail.model.Product;

/**
 * MyRetail Service a RESTful service that can retrieve product and price details by ID
 * @author ipseeta
 *
 */
@Path("/products")
public class MyRetailService {
	
	MyRetailDAO dao = new MyRetailDAO();
	/**
	 * Get product details by product id
	 * @param id request param
	 * @return product with 200 http status
	 */
	@GET @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response findById(@PathParam("id") String id) {
		if(Integer.parseInt(id)  < 0){
	        return Response.noContent().build();
	    }
		Product product = dao.findById(Integer.parseInt(id));
		return Response.status(200).entity(product).build(); 
	}
	/**
	 * It is a dummy method called only once to create price data in database
	 */
	@GET @Path("/create")
	@Produces({ MediaType.APPLICATION_JSON})
	public Response createDummyPriceData(){
		String success = dao.createDummyPriceData();
		return Response.status(200).entity(success).build(); 
	}
	/**
	 * Find product name by its id
	 * @param id
	 * @return name of the product
	 */
	@GET @Path("name/{id}")
	@Produces({ MediaType.APPLICATION_JSON})
	public Response findNameById(@PathParam("id") String id) {
		String name = dao.findNameById(Integer.parseInt(id));
		return Response.status(200).entity(name).build(); 
	}
	/**
	 * To update product price we call this update method
	 * @param id 
	 * @param product
	 * @return
	 */
	@PUT @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updatePrice(@PathParam("id") String id,Product product) {
		if(null == product.getCurrent_price()) {
	        return Response.status(400).entity("Please provide the product price").build();
	    }
		Product updatedProduct = dao.update(Integer.parseInt(id),product);
        return Response.status(200).entity(updatedProduct).build(); 
    }

}
