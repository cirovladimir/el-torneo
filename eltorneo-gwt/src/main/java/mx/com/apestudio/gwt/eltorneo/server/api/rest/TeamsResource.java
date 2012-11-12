/* Copyright (c) 2012, Ciro Vladimir Arreola Camacho. */
package mx.com.apestudio.gwt.eltorneo.server.api.rest;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mx.com.apestudio.gwt.eltorneo.server.model.DAO;
import mx.com.apestudio.gwt.eltorneo.server.model.Team;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/teams")
public class TeamsResource {
	@Resource
	MessageContext context;
	ObjectMapper om;
	Logger log;
	{
		om = new ObjectMapper();
		log = LoggerFactory.getLogger(TeamsResource.class);
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@Multipart("name") String name,
			@Multipart("logo") byte[] image) {
		try {
			Team team = new Team();
			team.setName(name);
			team.setLogo(image);
			DAO.create(team);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("id", team.getId());
			data.put("name", team.getName());
			String context = this.context.getUriInfo().getBaseUri()
					+ "/teams/";
			data.put("logo", new URL(context + team.getId() + "/logo.png"));
			return Response.ok(om.writeValueAsString(data),
					MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Response
					.ok("{\"response\": {\"status\": -1,\"data\": \"There was a problem saving data, try later.\"} }",
							MediaType.APPLICATION_JSON).build();
		}
	}

	@GET
	@Path("/{id}/logo.png")
	@Produces("image/png")
	public Response getLogo(@PathParam("id") Long id) {
		try {
			Team team = DAO.retrieve(Team.class, id);
			return Response.ok(team.getLogo().getBytes(), "image/png")
					.build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}
