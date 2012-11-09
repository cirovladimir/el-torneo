/* Copyright (c) 2012, Ciro Vladimir Arreola Camacho. */
package mx.com.apestudio.gwt.eltorneo.server.api.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;

/**
 * REST service for Player management
 * 
 * @author cirovladimir
 * 
 */
@Path("/players")
public class PlayersResource {

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response createPlayer(@Multipart("name") String name,
			@Multipart("lastname") String lastname,
			@Multipart("birthdate") String birthDate,
			@Multipart("height") Float height,
			@Multipart("weight") Float weight,
			@Multipart("email") String email,
			@Multipart("picture") byte[] picture) {
		return Response.ok("not implemented yet").build();
	}

}
