package com.twinklexsh.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twinklexsh.domain.Content;

@Service
public class PushNotificationService {

	public Boolean sendNotification(String gcmId) throws IOException{
		//gcmId = "APA91bF9AHpv4tOELZiskob_BOn7afucPjCqDaDuXy4Wq-y3ulQrhwZ6TTmC4L5bxXJlAx3e9hkT9IW43hZ15Sz-gCBAi_pTYg_s_rCp4-Ge9yKpuKc3EeN5yBNlRd0i3SgFj8vCbNFq";
		URL url = new URL("https://android.googleapis.com/gcm/send");
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
	    conn.setRequestProperty("Authorization", "key=AIzaSyCnzqUari8k5jh0wktNpB1cj7uNiYxmV8w");
	    conn.setDoOutput(true);
	    
	    ObjectMapper mapper = new ObjectMapper();
	    
	    DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
	     
	    Content c = new Content();

	    c.addRegId(gcmId);
	    c.createData("Test Title", "Test Message");
	     
	     mapper.writeValue(wr, c);
	     
	     wr.flush();
	     wr.close();
	     
	     int responseCode = conn.getResponseCode();
         System.out.println("\nSending 'POST' request to URL : " + url);
         System.out.println("Response Code : " + responseCode);

         BufferedReader in = new BufferedReader(
                   new InputStreamReader(conn.getInputStream()));
         
         String inputLine;
         StringBuffer response = new StringBuffer();

           while ((inputLine = in.readLine()) != null) {
               response.append(inputLine);
           }
           in.close();

           System.out.println(response.toString());	
           
         return true;
		
	}
}
