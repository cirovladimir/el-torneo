package mx.com.apestudio.gwt.eltorneo.client.modulos;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.FormElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class ModuloRegistroEquipo extends VLayout implements ClickHandler {
	
	public static class RegistroEquipoForm extends Widget{

		private InputElement fileInputElement;
		private InputElement nombreInputElement;

		public RegistroEquipoForm() {
			FormElement formElement = Document.get().createFormElement();
			formElement.setMethod("post");
			formElement.setEnctype("multipart/form-data");
			formElement.setAction("api/rest/equipos");
			fileInputElement = Document.get().createFileInputElement();
			fileInputElement.setAttribute("multiple", "multiple");
			formElement.appendChild(fileInputElement);
			nombreInputElement = Document.get().createTextInputElement();
			formElement.appendChild(nombreInputElement);
			setElement(formElement);
		}
		
		public InputElement getFileInputElement(){
			return fileInputElement;
		}
		public InputElement getNombreInputElement(){
			return nombreInputElement;
		}
	}

	private IButton btnEnviar;
	private RegistroEquipoForm form;
	public ModuloRegistroEquipo() {
		Canvas wrapper = new Canvas();
		form = new RegistroEquipoForm();
		wrapper.addChild(form);
		btnEnviar = new IButton("Enviar");
		btnEnviar.addClickHandler(this);
		setMembers(wrapper, btnEnviar);
	}

	@Override
	public void onClick(ClickEvent event) {
		Widget source = (Widget) event.getSource();
		if(btnEnviar == source){
			submit();
		}
	}

	private void submit() {
		submitNative(form.getFileInputElement(),form.getNombreInputElement().getValue(), "api/rest/equipos");
	}
	
	private void equipoFormDataSent(String response){
		SC.say("Response from server: "+response);
	}
	
	private native void submitNative(Element inputElement, String nombre, String url)/*-{
		var modulo = this;
		var input = inputElement, formdata=false;
		if($wnd.FormData){
			formdata = new FormData();
			var i = 0, len = input.files.length, file;
			for ( ; i < len; i++ ) {
				file = input.files[i];
				formdata.append("logo", file);
			}
			formdata.append("nombre", nombre);
			$wnd.$.ajax({
				url: url,
				type: "POST",
				data: formdata,
				processData: false,
				contentType: false,
				success: function (res) {
						modulo.@mx.com.apestudio.gwt.eltorneo.client.modulos.ModuloRegistroEquipo::equipoFormDataSent(Ljava/lang/String;)(res);
				}
			});
		}
	}-*/;
	
	
}
