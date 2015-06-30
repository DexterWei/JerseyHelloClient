package deter.restclient.tester;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import deter.restclient.TestClient;

public class tester {
	public static void main(String[] args) throws JSONException{
		String BootstrapUrl="http://localhost:8080/com.dexter.rest/bootstrap";
		TestClient client = new TestClient();
		client.Initiate("Haier","SmartFridge1", "20150101001",BootstrapUrl,new JSONObject().append("Sensor",2));
		if(client.SendBootstrapRequest())
			client.SendRegistrationRequest(0);
		client.SendUpdateRequest();
		pause();
		client.SendDeregisterRequest();
	}
	
	private static void pause(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
