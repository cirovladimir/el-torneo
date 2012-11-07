package mx.com.apestudio.gwt.eltorneo.client.events;

import com.google.web.bindery.event.shared.Event;

public class OpenModuleRequestedEvent extends Event<OpenModuleRequestedHandler>{
	
	private String moduleName;
	
	public OpenModuleRequestedEvent(String module) {
		this.moduleName=module;
	}
	
	public String getModuleName() {
		return moduleName;
	}

	public static final Type<OpenModuleRequestedHandler> TYPE=new Type<OpenModuleRequestedHandler>();

	@Override
	public Type<OpenModuleRequestedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(OpenModuleRequestedHandler handler) {
		handler.onModuleRequested(this);
	}

}
