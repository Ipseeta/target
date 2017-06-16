package com.myRetail.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
/**
 * If wrong id is given during search, this custome exception is called
 * @author ipseeta
 *
 */
@Provider
public class ProductSearchExceptionMapper implements ExceptionMapper<ProductSearchException>{

	public Response toResponse(ProductSearchException ex)
	{
		return Response.status(Status.NOT_FOUND)
				.entity(new ErrorProps("404", ex.getMessage()))
				.build();
	}
}