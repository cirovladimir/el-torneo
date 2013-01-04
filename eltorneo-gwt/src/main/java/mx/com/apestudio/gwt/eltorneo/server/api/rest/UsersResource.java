/* Copyright (c) 2012, Ciro Vladimir Arreola Camacho. */
package mx.com.apestudio.gwt.eltorneo.server.api.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.com.apestudio.gwt.eltorneo.server.model.DAO;
import mx.com.apestudio.gwt.eltorneo.server.model.User;

@Path("/users")
public class UsersResource {
	final Logger log = LoggerFactory.getLogger(UsersResource.class);

	@POST
	@Path("/register")
	public Response register(@FormParam("name") String name,
			@FormParam("lastname") String lastname,
			@FormParam("email") String email) {
		try {
			User user = new User();
			user.setName(name);
			user.setLastname(lastname);
			user.setEmail(email);
			DAO.create(user);
			// send verification email
			RestResponse response = new RestResponse(0, user);
			return Response.ok(Jackson.om().writeValueAsString(response),
					MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return Response
				.ok("{\"response\": {\"status\": -1,\"data\": \"There was a problem saving data, try later.\"} }",
						MediaType.APPLICATION_JSON).build();
	}
}
