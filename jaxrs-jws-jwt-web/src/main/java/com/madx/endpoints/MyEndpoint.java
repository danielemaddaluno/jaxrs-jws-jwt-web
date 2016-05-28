package com.madx.endpoints;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/test")
public class MyEndpoint implements IEndpoint{

	@Context
	SecurityContext sctx;
	
    @Override
    public Response myUnsecuredMethod(@PathParam("id") Long id) {
    	return Response.ok("unsecure").build();
    }

    @Override
    public Response mySecuredMethod(@PathParam("id") Long id) {
    	return Response.ok("secure" + sctx.getUserPrincipal().getName()).build();
    }
}