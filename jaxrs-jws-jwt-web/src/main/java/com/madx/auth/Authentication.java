package com.madx.auth;

import javax.ejb.Local;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.madx.auth.credentials.Credentials;

@Local
@Path("auth")
public interface Authentication {
	@POST
    @Produces(MediaType.APPLICATION_JSON )
    @Consumes(MediaType.APPLICATION_JSON )
    @Path("/auth")
    public Response authenticateUser(Credentials credentials);
}
