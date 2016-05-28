package com.madx.endpoints;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;


@Stateless( name = "RESTCorsDemoResource", mappedName = "ejb/RESTCorsDemoResource" )
public class RESTCorsDemoResource implements RESTCorsDemoResourceProxy {
	private static final long serialVersionUID = 4873062465782818180L;

	@Override
	public Response getMethod() {
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		jsonObjBuilder.add( "message", "get method ok" );

		JsonObject jsonObj = jsonObjBuilder.build();

		return Response.status( Response.Status.OK ).entity( jsonObj.toString() ).build();
	}

	@Override
	public Response putMethod() {
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		jsonObjBuilder.add( "message", "get method ok" );

		JsonObject jsonObj = jsonObjBuilder.build();

		return Response.status( Response.Status.ACCEPTED ).entity( jsonObj.toString() ).build();
	}

	@Override
	public Response postMethod() {
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		jsonObjBuilder.add( "message", "post method ok" );

		JsonObject jsonObj = jsonObjBuilder.build();

		return Response.status( Response.Status.CREATED ).entity( jsonObj.toString() ).build();
	}

	@Override
	public Response deleteMethod() {
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		jsonObjBuilder.add( "message", "delete method ok" );

		JsonObject jsonObj = jsonObjBuilder.build();

		return Response.status( Response.Status.ACCEPTED ).entity( jsonObj.toString() ).build();
	}


	/**
	 * http://localhost:8080/nights-web/rest-api/rest-cors-demo/home/
	 */
	@Override
	public String home(HttpServletRequest request) {
		//		String name = "World";
		//
		//		Account account = AccountResolver.INSTANCE.getAccount(request);
		//		if (account != null) {
		//			name = account.getGivenName();
		//		}
		//
		//		return "Hello " + name + "!";
		return "";
	}

}