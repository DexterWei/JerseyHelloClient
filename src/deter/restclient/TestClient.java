package deter.restclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;
 
public class TestClient{
  private Integer id;
  public void SetID(Integer i){
	  id=i;
  }
  public void SendPostRequest(String msg) {
	try {
		Client client = Client.create();
		WebResource r = client.resource("http://localhost:8080/com.dexter.rest/rest/chats/post");
		
		String input = "{\"sender\":\""+id.toString()+"\",\"receiver\":\"0\",\"message\":\""+msg+"\"}";
		//System.out.println(input);
		ClientResponse response = r.type("application/json")
				   .post(ClientResponse.class, input);

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