package com.madx.endpoints;

import javax.ejb.Local;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.madx.auth.annotations.Secured;

@Local
@Path("/test")
public interface IEndpoint {
	/**
	 * This method is not annotated with @Secured
	 * The authentication filter won't be executed before invoking this method
	 * @param id
	 * @return
	 */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response myUnsecuredMethod(Long id);

    /**
     * 
     * This method is annotated with @Secured
     * The authentication filter will be executed before invoking this method
     * The HTTP request must be performed with a valid token
     * @param id
     * @return
     */
    @DELETE
    @Secured
    @Path("{id}")
    @Produces("application/json")
    public Response mySecuredMethod(Long id);
}