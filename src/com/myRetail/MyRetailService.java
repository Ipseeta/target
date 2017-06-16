package com.myRetail;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/products")
public class MyRetailService {
	
	MyRetailDAO dao = new MyRetailDAO();
	
	@GET @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Product findById(@PathParam("id") String id) {
		System.out.println("findById " + id);
		return dao.findById(Integer.parseInt(id));
	}
	
	@GET @Path("/create")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Price> createDummyPriceData(){
		return dao.createDummyPriceData();
	}
	
	@GET @Path("name/{id}")
	@Produces({ MediaType.TEXT_PLAIN})
	public String findNameById(@PathParam("id") String id) {
		System.out.println("findNameById " + id);
		return dao.findNameById(Integer.parseInt(id));
	}
	
	@PUT @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Product update(@PathParam("id") String id,Product product) {
        return dao.update(Integer.parseInt(id),product);
    }

}
