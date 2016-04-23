package com.twinklexsh.services;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

	public Boolean sendSms(String phoneNumber) throws TwilioRestException{
		String ACCOUNT_SID = "AC1cc1afb79fd6e76ce2862e97626da837";
		String AUTH_TOKEN = "a6691088929fbcb5078f7a430658a86e";
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		 
	    // Build a filter for the MessageList
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("Body", "Pls get ready."));
	    params.add(new BasicNameValuePair("To", phoneNumber));
	    params.add(new BasicNameValuePair("From", "+12402932759"));
	 
	    MessageFactory messageFactory = client.getAccount().getMessageFactory();
	    Message message = messageFactory.create(params);
	    System.out.println(message.getSid());
		
	    return true;
	}
}
