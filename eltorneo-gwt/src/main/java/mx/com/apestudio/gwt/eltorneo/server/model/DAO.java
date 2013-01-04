package mx.com.apestudio.gwt.eltorneo.server.model;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class DAO {

	static{
		factory().register(Team.class);
		factory().register(User.class);
	}
	
	public static ObjectifyFactory factory(){
		return ObjectifyService.factory();
	}
	
	public static Objectify ofy(){
		return factory().begin();
	}
	
	public static Team create(Team team) {
		ofy().put(team);
		return team;
	}
	
	public static Team retrieve(Class<Team> type, Long id){
		return ofy().get(type, id);
	}
	
	public static User create(User user){
		ofy().put(user);
		return user;
	}
	
}
