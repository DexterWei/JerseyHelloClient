package deter.restclient.tester;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import deter.restclient.TestClient;

public class tester {
	public static void main(String[] args){
		String BootstrapUrl="http://localhost:8080/com.dexter.rest/rest/chats/post";
		TestClient client = new TestClient();
		client.Initiate("XiaoMi","TVBox", "TV20150101001",BootstrapUrl);
		client.SendPostRequest();
	}
}
