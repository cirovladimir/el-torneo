package mx.com.apestudio.gwt.eltorneo.client.modules;

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

public class TeamRegistrationModule extends VLayout implements ClickHandler {
	
	public static class TeamRegistrationForm extends Widget{

		private InputElement fileInputElement;
		private InputElement nameInputElement;

		public TeamRegistrationForm() {
			FormElement formElement = Document.get().createFormElement();
			formElement.setMethod("post");
			formElement.setEnctype("multipart/form-data");
			formElement.setAction("api/rest/teams");
			fileInputElement = Document.get().createFileInputElement();
			fileInputElement.setName("logo");
			fileInputElement.setAttribute("multiple", "multiple");
			formElement.appendChild(fileInputElement);
			nameInputElement = Document.get().createTextInputElement();
			nameInputElement.setName("name");
			formElement.appendChild(nameInputElement);
			setElement(formElement);
		}
		
		public InputElement getFileInputElement(){
			return fileInputElement;
		}
		public InputElement getNameInputElement(){
			return nameInputElement;
		}
	}

	private IButton btnSubmit;
	private TeamRegistrationForm form;
	
	public TeamRegistrationModule() {
		Canvas wrapper = new Canvas();
		form = new TeamRegistrationForm();
		wrapper.addChild(form);
		btnSubmit = new IButton("Enviar");
		btnSubmit.addClickHandler(this);
		setMembers(wrapper, btnSubmit);
	}

	@Override
	public void onClick(ClickEvent event) {
		Widget source = (Widget) event.getSource();
		if(btnSubmit == source){
			submit();
		}
	}

	private void submit() {
		submitNative(form.getFileInputElement(),form.getNameInputElement().getValue(), "api/rest/teams");
	}
	
	private void teamFormDataSent(String response){
		SC.say("Response from server: "+response);
	}
	
	private native void submitNative(Element inputElement, String name, String url)/*-{
		var modulo = this;
		var input = inputElement, formdata=false;
		if($wnd.FormData){
			formdata = new FormData();
			var i = 0, len = input.files.length, file;
			for ( ; i < len; i++ ) {
				file = input.files[i];
				formdata.append("logo", file);
			}
			formdata.append("name", name);
			$wnd.$.ajax({
				url: url,
				type: "POST",
				data: formdata,
				processData: false,
				contentType: false,
				success: function (res) {
						modulo.@mx.com.apestudio.gwt.eltorneo.client.modules.TeamRegistrationModule::teamFormDataSent(Ljava/lang/String;)(res);
				}
			});
		}
	}-*/;
	
	
}
