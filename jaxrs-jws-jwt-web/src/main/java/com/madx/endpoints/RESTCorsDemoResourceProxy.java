package com.madx.endpoints;

import java.io.Serializable;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Local
@Path("rest-cors-demo")
public interface RESTCorsDemoResourceProxy extends Serializable {

	/**
	 * http://localhost:8080/nights-web/rest-api/rest-cors-demo/get-method/
	 * @return
	 */
    @GET
    @Path("get-method")
    @Produces( MediaType.APPLICATION_JSON )
    public Response getMethod();

    /**
     * http://localhost:8080/nights-web/rest-api/rest-cors-demo/put-method/
     * @return
     */
    @PUT
    @Path("put-method")
    @Produces( MediaType.APPLICATION_JSON )
    public Response putMethod();

    /**
     * http://localhost:8080/nights-web/rest-api/rest-cors-demo/post-method/
     * @return
     */
    @POST
    @Path("post-method")
    @Produces( MediaType.APPLICATION_JSON )
    public Response postMethod();

    /**
     * http://localhost:8080/nights-web/rest-api/rest-cors-demo/delete-method/
     * @return
     */
    @DELETE
    @Path("delete-method")
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteMethod();
    
    @Path("/home")
    String home(HttpServletRequest request);
    
    
    
}