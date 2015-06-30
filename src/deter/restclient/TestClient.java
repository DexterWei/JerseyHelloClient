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
	private int RegisterAt;
	private JSONObject Resources=null;
	
	
	public void Initiate(String man,String type,String sn,String url,JSONObject rsc){
		Manufacturer = man;
		Model=type;
		SerialNumber=sn; 
		BootStrapUrl=url;//"http://localhost:8080/com.dexter.rest/rest/chats/post"
		Resources = rsc;
	}
	public boolean SendBootstrapRequest() {
		try {
			Client client = Client.create();
			WebResource r = client.resource(BootStrapUrl);
			JSONObject model = new JSONObject().put("Manufacturer",Manufacturer)
												.put("Model",Model)
												.put("SN",SerialNumber);
			
			System.out.println("\n Bootstraping .... ");
			ClientResponse response = r.type("application/json")
					   .post(ClientResponse.class, model);
	
			if (response.getStatus() > 202) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}
			
			String retstr=response.getEntity(String.class);
			JSONObject rtn = new JSONObject(retstr);
			if(!rtn.has("status")){
				//System.out.println(rtn.get("RegistrationURL").toString());
				JSONArray urls = (JSONArray)rtn.get("RegistrationUrl");
				for(int i=0;i<urls.length();i++){
					RegistrationUrl.add((String)urls.get(i));
				}
				System.out.println("Output from Server .... \n");
				System.out.println(retstr);
				return true;
			}else{
				System.out.println("Output from Server .... \n");
				System.out.println(retstr);
				return false;
			}
		  } catch (Exception e) {
			e.printStackTrace();
			return false;
		  }
		}
	
	public void SendRegistrationRequest(int server) {
		try {
			Client client = Client.create();
			WebResource r = client.resource(RegistrationUrl.get(server));
			JSONObject model = new JSONObject().put("Request","Register")
												.put("Manufacturer",Manufacturer)
												.put("Model",Model)
												.put("SN",SerialNumber)
												.put("Resources", Resources);
			
			System.out.println("\n Signing in .... ");
			ClientResponse response = r.type("application/json")
					   .post(ClientResponse.class, model);
	
			if (response.getStatus() > 202) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}
			
			String retstr=response.getEntity(String.class);			
			System.out.println("Output from Server .... \n");
			System.out.println(retstr);
			
		  } catch (Exception e) {
			e.printStackTrace();
		  }
		}
	
	public boolean SendUpdateRequest() {
		try {
			Client client = Client.create();
			WebResource r = client.resource(RegistrationUrl.get(RegisterAt));
			JSONObject model = new JSONObject().put("Request","Update")
												.put("Manufacturer",Manufacturer)
												.put("Model",Model)
												.put("SN",SerialNumber)
												.put("Resources", Resources);
			
			System.out.println("\n updating .... \n");
			ClientResponse response = r.type("application/json")
					   .post(ClientResponse.class, model);
	
			if (response.getStatus() > 202) {
				System.out.println("Failed : HTTP error code : "
				+ response.getStatus()+response.toString());
				return false;
			}
			
			String retstr=response.getEntity(String.class);			
			System.out.println("Output from Server .... \n");
			System.out.println(retstr);
			return true;
		  } catch (Exception e) {
			e.printStackTrace();
			return false;
		  }
		}
	
	public boolean SendDeregisterRequest() {
		try {
			Client client = Client.create();
			WebResource r = client.resource(RegistrationUrl.get(RegisterAt));
			JSONObject model = new JSONObject().put("Request","De-register")
												.put("Manufacturer",Manufacturer)
												.put("Model",Model)
												.put("SN",SerialNumber)
												.put("Resources", Resources);
			
			System.out.println("\n De-registering .... \n");
			ClientResponse response = r.type("application/json")
					   .post(ClientResponse.class, model);
	
			if (response.getStatus() > 202) {
			   System.out.println("Failed : HTTP error code : "
				+ response.getStatus()+response.toString());
			   return false;
			}
			
			String retstr=response.getEntity(String.class);			
			System.out.println("Output from Server .... \n");
			System.out.println(retstr);
			return true;
		  } catch (Exception e) {
			e.printStackTrace();
			return false;
		  }
		}
	
}

