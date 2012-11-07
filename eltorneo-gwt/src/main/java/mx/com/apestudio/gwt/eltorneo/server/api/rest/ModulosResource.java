package mx.com.apestudio.gwt.eltorneo.server.api.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import mx.com.apestudio.gwt.eltorneo.server.modelo.Modulo;
import mx.com.apestudio.gwt.eltorneo.server.smartgwt.SCResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/modulos")
public class ModulosResource {
	ObjectMapper om;
	Logger log;
	{
		om = new ObjectMapper();
		log = LoggerFactory.getLogger(ModulosResource.class);
	}

	@GET
	public String retrieve(){
		try {
			List<Modulo> modulos = Modulo.getModulos();
			return om.writeValueAsString(new SCResponse(0, modulos));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "{\"response\": {\"status\": -1,\"data\": \"Hubo un problema al cargar los datos, intente más tarde\"} }";
	}
}
