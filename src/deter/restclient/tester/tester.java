package deter.restclient.tester;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import deter.restclient.TestClient;

public class tester {
	public static void main(String[] args){
		//prepare 3 clients
		ArrayList<TestClient> clients = new ArrayList<TestClient>();
		for(int i=0;i<3;i++){
			TestClient t = new TestClient();
			t.SetID(i+1);
			clients.add(t);
		}

		//input client number to send current time to the server
		//echo message history from server
	    System.out.println("select client #:");
		Scanner sc = new Scanner(System.in);
	    int c_id = sc.nextInt();
		while(c_id>0 && c_id<4){
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			Calendar calobj = Calendar.getInstance();
			clients.get(c_id-1).SendPostRequest(df.format(calobj.getTime()));
			System.out.println("select client #:");
			c_id = sc.nextInt();
		}
	}
}
