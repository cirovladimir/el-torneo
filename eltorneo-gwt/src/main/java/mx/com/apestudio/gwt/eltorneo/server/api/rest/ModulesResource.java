package mx.com.apestudio.gwt.eltorneo.server.api.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import mx.com.apestudio.gwt.eltorneo.server.model.Module;
import mx.com.apestudio.gwt.eltorneo.server.smartgwt.SCResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/modules")
public class ModulesResource {
	ObjectMapper om;
	Logger log;
	{
		om = new ObjectMapper();
		log = LoggerFactory.getLogger(ModulesResource.class);
	}

	@GET
	public String retrieve(){
		try {
			List<Module> modules = Module.getModules();
			return om.writeValueAsString(new SCResponse(0, modules));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "{\"response\": {\"status\": -1,\"data\": \"There was a problem loading data, try later.\"} }";
	}
}
