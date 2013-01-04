/* Copyright (c) 2012, Ciro Vladimir Arreola Camacho. */
package mx.com.apestudio.gwt.eltorneo.server.api.rest;

import org.codehaus.jackson.map.ObjectMapper;

public class Jackson {

	private static ObjectMapper om;
	
	public static ObjectMapper om(){
		if(om==null){
			om = new ObjectMapper();
		}
		return om;
	}
	
}
