package deter.restclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;
 
public class TestClient{
 
  public static void main(String[] args) {
	try {
		Client client = Client.create();
		WebResource r = client.resource("http://localhost:8080/com.dexter.rest/rest/chats/post");
		
		String input = "{\"sender\":\"Client1\",\"receiver\":\"server\",\"message\":\"message_1\"}";
		ClientResponse response = r.type("application/json")
				   .post(ClientResponse.class, input);
		 
		
		/*ClientResponse response = r.
				       type(MediaType.TEXT_PLAIN)
				      .accept(MediaType.TEXT_PLAIN)
				      .post(ClientResponse.class, "this is dexter");

		
		String name = "Dexter";
		Client client = Client.create();
 
		WebResource webResource = client
		   .resource("http://localhost:8080/com.dexter.rest/rest/hello/"+name);
 
		ClientResponse response = webResource.accept(MediaType.TEXT_PLAIN)
                   .get(ClientResponse.class);
 */
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