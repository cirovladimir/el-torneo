/* Copyright (c) 2012, Ciro Vladimir Arreola Camacho. */
package mx.com.apestudio.gwt.eltorneo.server.api.rest;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONResponse extends RestResponse{
	
	private static ObjectMapper om;
	
	private static ObjectMapper om(){
		if(om == null){
			om = new ObjectMapper();
		}
		return om;
	}
	
	public JSONResponse(Integer status, Object data) {
		super(status, data);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		try {
			om().writeValueAsString(this);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "{}";
	}

}
