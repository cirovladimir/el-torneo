package mx.com.apestudio.gwt.eltorneo.server.modelo;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class DAO {

	static{
		factory().register(Equipo.class);
	}
	
	public static ObjectifyFactory factory(){
		return ObjectifyService.factory();
	}
	
	public static Objectify ofy(){
		return factory().begin();
	}
	
	public static Equipo create(Equipo equipo) {
		ofy().put(equipo);
		return equipo;
	}
	
	public static Equipo retrieve(Class<Equipo> type, Long id){
		return ofy().get(type, id);
	}
	
}
