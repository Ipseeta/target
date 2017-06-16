package com.myRetail.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	public Response toResponse(Throwable ex){
		if(ex instanceof ProductSearchException){

			return Response.status(Status.NOT_FOUND)
					.entity(new ErrorProps("404", ex.getMessage()))
					.build();
		}else{
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ErrorProps("Some error code, 500 or somthing", ex.getMessage()))
					.build();
		}
	}

}