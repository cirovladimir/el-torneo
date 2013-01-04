/* Copyright (c) 2012, Ciro Vladimir Arreola Camacho. */
package mx.com.apestudio.gwt.eltorneo.server.api.rest;

public class RestResponse {
	
	public static class ResponseData{
		public Integer status;
		public Object data;
		public Integer start;
		public Integer end;
		public Integer total;
	}
	
	ResponseData response;
	
	public RestResponse(Integer status, Object data) {
		response.status = status;
		response.data = data;
	}

	public ResponseData getResponse() {
		return response;
	}

	public void setResponse(ResponseData response) {
		this.response = response;
	}

}
