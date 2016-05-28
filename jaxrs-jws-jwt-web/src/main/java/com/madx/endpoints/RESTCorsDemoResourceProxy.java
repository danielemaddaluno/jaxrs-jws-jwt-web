package com.madx.endpoints;

import java.io.Serializable;

import javax.ejb.Local;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * http://localhost:8080/jaxrs-jws-jwt-web/rest-api/rest-cors-demo/
 * @author madx
 *
 */
@Local
@Path("rest-cors-demo")
public interface RESTCorsDemoResourceProxy extends Serializable {

	@GET
	@Path("get-method")
	@Produces( MediaType.APPLICATION_JSON )
	public Response getMethod();

	@PUT
	@Path("put-method")
	@Produces( MediaType.APPLICATION_JSON )
	public Response putMethod();

	@POST
	@Path("post-method")
	@Produces( MediaType.APPLICATION_JSON )
	public Response postMethod();

	@DELETE
	@Path("delete-method")
	@Produces( MediaType.APPLICATION_JSON )
	public Response deleteMethod();
}