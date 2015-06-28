package deter.restclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;
 
public class TestClient{
	private String Type;
	private String SerialNumber;
	private String BootStrapUrl;
	
	public void Initiate(String type,String sn,String url){
		Type=type;
		SerialNumber=sn; 
		BootStrapUrl=url;//"http://localhost:8080/com.dexter.rest/rest/chats/post"
	}
  public void SendPostRequest() {
	try {
		Client client = Client.create();
		WebResource r = client.resource(BootStrapUrl);
		JSONObject model = new JSONObject().put("Type",Type).put("SN",SerialNumber);
		ClientResponse response = r.type("application/json")
				   .post(ClientResponse.class, model);

		if (response.getStatus() != 201) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}
		String output = response.getEntity(String.class);
		System.out.println("Output from Server .... \n");
		System.out.println(output);
	  } catch (Exception e) {
		e.printStackTrace();
	  }
	}
}