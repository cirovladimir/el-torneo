package mx.com.apestudio.gwt.eltorneo.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class FileUploadEvent extends GwtEvent<FileUploadEvent.Handler> {
	
	public interface Handler extends EventHandler{
		void onFileUpload(FileUploadEvent event);
	}
	
	public static Type<FileUploadEvent.Handler> TYPE= new Type<FileUploadEvent.Handler>();

	@Override
	public Type<FileUploadEvent.Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(FileUploadEvent.Handler handler) {
		handler.onFileUpload(this);
	}
	
	String response;
	
	public FileUploadEvent(String response) {
		this.response=response;
	}
	
	public String getResponse(){
		return this.response;
	}

}
