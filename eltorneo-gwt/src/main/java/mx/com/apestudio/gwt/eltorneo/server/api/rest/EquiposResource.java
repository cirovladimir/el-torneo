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

import mx.com.apestudio.gwt.eltorneo.server.modelo.DAO;
import mx.com.apestudio.gwt.eltorneo.server.modelo.Equipo;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/equipos")
public class EquiposResource {
	@Resource
	MessageContext context;
	ObjectMapper om;
	Logger log;
	{
		om = new ObjectMapper();
		log = LoggerFactory.getLogger(EquiposResource.class);
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@Multipart("nombre") String nombre,
			@Multipart("logo") byte[] image) {
		try {
			Equipo equipo = new Equipo();
			equipo.setNombre(nombre);
			equipo.setLogo(image);
			DAO.create(equipo);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("id", equipo.getId());
			data.put("nombre", equipo.getNombre());
			String context = this.context.getUriInfo().getBaseUri()
					+ "/equipos/";
			data.put("logo", new URL(context + equipo.getId() + "/logo.png"));
			return Response.ok(om.writeValueAsString(data),
					MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Response
					.ok("{\"response\": {\"status\": -1,\"data\": \"Hubo un problema al guardar los datos, intente más tarde\"} }",
							MediaType.APPLICATION_JSON).build();
		}
	}

	@GET
	@Path("/{id}/logo.png")
	@Produces("image/png")
	public Response getLogo(@PathParam("id") Long id) {
		try {
			Equipo equipo = DAO.retrieve(Equipo.class, id);
			return Response.ok(equipo.getLogo().getBytes(), "image/png")
					.build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}
