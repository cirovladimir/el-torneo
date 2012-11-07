package mx.com.apestudio.gwt.eltorneo.client;

import mx.com.apestudio.gwt.eltorneo.client.modulos.ModuloAdministracion;

import com.google.gwt.core.client.EntryPoint;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class ElTorneoEntryPoint implements EntryPoint {
	private EventBus eventBus;

	@Override
	public void onModuleLoad() {
		eventBus = new SimpleEventBus();
		ModuloAdministracion admon = new ModuloAdministracion(eventBus);
		admon.draw();
	}

}
