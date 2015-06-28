package deter.restclient;

import java.util.ArrayList;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
 
public class TestClient{
	private String Manufacturer;
	private String Model;
	private String SerialNumber;
	private String BootStrapUrl;
	private ArrayList<String> RegistrationUrl = new ArrayList<String>();
	
	public void Initiate(String man,String type,String sn,String url){
		Manufacturer = man;
		Model=type;
		SerialNumber=sn; 
		BootStrapUrl=url;//"http://localhost:8080/com.dexter.rest/rest/chats/post"
	}
  public void SendPostRequest() {
	try {
		Client client = Client.create();
		WebResource r = client.resource(BootStrapUrl);
		JSONObject model = new JSONObject().put("Manufacturer",Manufacturer).put("Model",Model).put("SN",SerialNumber);
		ClientResponse response = r.type("application/json")
				   .post(ClientResponse.class, model);

		if (response.getStatus() != 201) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}
		
		String retstr=response.getEntity(String.class);
		JSONObject rtn = new JSONObject(retstr);
		if(!rtn.has("status")){
			//System.out.println(rtn.get("RegistrationURL").toString());
			JSONArray urls = (JSONArray)rtn.get("RegistrationURL");
			for(int i=0;i<urls.length();i++){
				RegistrationUrl.add((String)urls.get(i));
			}
		}
		
		System.out.println("Output from Server .... \n");
		System.out.println(retstr);
		for(int i=0;i<RegistrationUrl.size();i++)
			System.out.println(RegistrationUrl.get(i));
	  } catch (Exception e) {
		e.printStackTrace();
	  }
	}
}