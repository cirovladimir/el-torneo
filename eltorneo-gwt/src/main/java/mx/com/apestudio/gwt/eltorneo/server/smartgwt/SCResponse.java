package mx.com.apestudio.gwt.eltorneo.server.smartgwt;

public class SCResponse {

	public static class Response{
		public Integer status;
		public Object data;
		public Response(Integer status, Object data) {
			this.status = status;
			this.data = data;
		}
	}
	
	Response response;
	
	public SCResponse(Integer status, Object data) {
		this.response = new Response(status, data);
	}

	public Response getResponse() {
		return response;
	}
	
}
