package mx.com.apestudio.gwt.eltorneo.client.widgets;

import mx.com.apestudio.gwt.eltorneo.client.events.FileUploadEvent;

import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.FormElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;

public class FileUploadButton extends Widget{
	
	private ButtonElement submitButtonElement;
	private String action;
	
	public FileUploadButton(String action) {
		this.action=action;
		FormElement formElement = Document.get().createFormElement();
		formElement.setMethod("post");
		formElement.setEnctype("multipart/form-data");
		formElement.setAction(this.getAction());
		InputElement fileInputElement = Document.get().createFileInputElement();
		fileInputElement.setId("fileInputElement");
		fileInputElement.setName("file");
		fileInputElement.setAttribute("multiple", "multiple");
		DOM.sinkEvents((Element) Element.as(fileInputElement), Event.ONCHANGE);
		formElement.appendChild(fileInputElement);
		submitButtonElement = Document.get().createSubmitButtonElement();
		submitButtonElement.setId("submitButtonElement");
		submitButtonElement.setInnerText("Upload");
		formElement.appendChild(submitButtonElement);
		
		setElement(formElement);
	}
	
	@Override
	protected void onLoad() {
		initializeJSNI();
	}
	
	private String getAction(){
		return this.action;
	}
	
	private native final void initializeJSNI() /*-{
		var thiz=this;
		var input = $doc.getElementById("fileInputElement"), formdata = false;

		if ($wnd.FormData) {
	  		formdata = new FormData();
	  		$doc.getElementById("submitButtonElement").style.display = "none";
		}
	
 		input.addEventListener("change", function (evt) {
	 		var i = 0, len = this.files.length, img, reader, file;
		
			for ( ; i < len; i++ ) {
				file = this.files[i];
				if (formdata) {
					formdata.append("files[]", file);
				}
			}
		
			if (formdata) {
				$wnd.$.ajax({
					url: thiz.@mx.com.apestudio.gwt.eltorneo.client.widgets.FileUploadButton::getAction()(),
					type: "POST",
					data: formdata,
					processData: false,
					contentType: false,
					success: function (res) {
						thiz.@mx.com.apestudio.gwt.eltorneo.client.widgets.FileUploadButton::fileUploadSucceded(Ljava/lang/String;)(res);
					}
				});
			}
 		}, false);
	}-*/;
	
	private void fileUploadSucceded(String response){
		fireEvent(new FileUploadEvent(response));
	}

}
