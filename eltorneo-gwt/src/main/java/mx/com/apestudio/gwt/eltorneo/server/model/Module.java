package mx.com.apestudio.gwt.eltorneo.server.model;

import java.util.ArrayList;
import java.util.List;

public class Module {

	Long id;
	String name;
	Long idParent;
	
	public Module(Long id, String name, Long idParent) {
		this.id = id;
		this.name = name;
		this.idParent = idParent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
	}

	public Long getIdParent() {
		return idParent;
	}

	public void setIdParent(Long idParent) {
		this.idParent = idParent;
	}
	
	public static List<Module> getModules(){
		List<Module> modules = new ArrayList<Module>();
		modules.add(new Module(1L, "Team Registration", null));
		return modules;
	}
	
}
