package com.twinklexsh.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.twilio.sdk.TwilioRestException;
import com.twinklexsh.domain.Content;
import com.twinklexsh.domain.Restaurant;
import com.twinklexsh.domain.RestaurantDetails;
import com.twinklexsh.domain.RestaurantTable;
import com.twinklexsh.domain.UserEvents;
import com.twinklexsh.domain.UserPlan;
import com.twinklexsh.domain.Users;
import com.twinklexsh.repository.RestaurantDetailsRepository;
import com.twinklexsh.repository.RestaurantRepository;
import com.twinklexsh.repository.UserEventsRepository;
import com.twinklexsh.repository.UsersRepository;
import com.twinklexsh.services.PushNotificationService;
import com.twinklexsh.services.SmsService;

import javax.inject.Inject;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.android.gcm.server.Message;
//import com.google.android.gcm.server.Result;
//import com.google.android.gcm.server.Sender;
//import com.twilio.sdk.TwilioRestClient;
//import com.twilio.sdk.TwilioRestException;
//import com.twilio.sdk.resource.factory.MessageFactory;
//import com.twilio.sdk.resource.instance.Message;
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;



//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class AdminResource {
	
	@Inject
	RestaurantRepository restaurantRepository;
	
	@Inject
	RestaurantDetailsRepository restaurantDetailsRepository;
	
	@Inject
	UserEventsRepository userEventsRepository;
	
	@Inject
    private PushNotificationService pushNotificationService;
	
	@Inject
    private SmsService smsService;

	@RequestMapping(value="/getUserList", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserEvents> getUserList() {
		return null;
	}

	@RequestMapping(value="/getUserList/{pId}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserEvents> getUserList(@PathVariable Long pId) {
		Restaurant rs = restaurantRepository.findOne(pId);
		
		List<RestaurantDetails> rsDetailsList =  restaurantDetailsRepository.findByRestaurant(rs);
		
		List<String> statusList = new ArrayList<String>();
		statusList.add("booked");
		statusList.add("servicing");
		statusList.add("ready");
		
		LocalDate dateToday = LocalDate.now();
		
		List<UserEvents> usList = userEventsRepository.findByRequestTimeAndStatusTableInAndRestaurantDetailsIn(dateToday, statusList, rsDetailsList);
		return usList;
	}
	
	@RequestMapping(value="/releaseUser", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserEvents> releaseUser() {
		return null;
	}

	@RequestMapping(value="/releaseUser/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserEvents> releaseUserById(@PathVariable Long id) {
		
		// update to done status.
		UserEvents uvs = userEventsRepository.findOne(id);
		
		uvs.setStatusTable("done");
		userEventsRepository.save(uvs);
		
		// for get the user request details and find the next person.
		//RestaurantDetails rsDetails = uvs.getRestaurantDetails();
		
		//List<RestaurantDetails> allRsDetails = restaurantDetailsRepository.findByTableSeatGreaterThanEqualAndSmokingAndRestaurant(rsDetails.getTableSeat(), rsDetails.isSmoking(), rsDetails.getRestaurant());
		
		//UserEvents nextUser = userEventsRepository.
		List<UserEvents> latestUserEvents = getUserList(uvs.getRestaurantDetails().getRestaurant().getId());
		
		return latestUserEvents;
	}
	
	@RequestMapping(value="/serveUser", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserEvents> serveUser() {
		return null;
	}

	@RequestMapping(value="/serveUser/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserEvents> serveUserById(@PathVariable Long id) {
		
		UserEvents uvs = userEventsRepository.findOne(id);
		
		List<RestaurantDetails> rsDetailsList = restaurantDetailsRepository.findByTableSeatGreaterThanEqualAndSmokingAndRestaurant(uvs.getRestaurantDetails().getTableSeat(), uvs.getRestaurantDetails().isSmoking(), uvs.getRestaurantDetails().getRestaurant());
		LocalDate dateToday = LocalDate.now();
		List<String> inprogress = new ArrayList<String>();
		inprogress.add("servicing");
		
		List<UserEvents> servicingList = userEventsRepository.findByRequestTimeAndStatusTableInAndRestaurantDetailsInOrderByUsersAsc(dateToday, inprogress, rsDetailsList);

		System.out.println("Checked Seat now");
		if(servicingList.size() - rsDetailsList.size() <0 ){
			//which mean user clicked wrong button, there no more seat available now, due to servicing tale is full now. please release someone before serve raedy person.
			System.out.println("okay now, there is a seat available now");
			uvs.setStatusTable("servicing");
			userEventsRepository.save(uvs);
			
			List<String> statusList = new ArrayList<String>();
			statusList.add("booked");
			
			
			
			List<UserEvents> uvsList = userEventsRepository.findByRequestTimeAndStatusTableInAndRestaurantDetailsInOrderByUsersAsc(dateToday, statusList, rsDetailsList);
			
			if(uvsList.size() > 0){
				uvsList.get(0).setStatusTable("ready");
				userEventsRepository.save(uvsList.get(0));
			}
		}
		System.out.println("Checked Seat end");
		
		List<UserEvents> latestUserEvents = getUserList(uvs.getRestaurantDetails().getRestaurant().getId());
		
		return latestUserEvents;
	}

	@RequestMapping(value="/cancelUser", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserEvents> cancelUser() {
		return null;
	}

	@RequestMapping(value="/cancelUser/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserEvents> cancelUserById(@PathVariable Long id) {
		
		UserEvents uvs = userEventsRepository.findOne(id);
		uvs.setStatusTable("rejected");
		userEventsRepository.save(uvs);
		
		
		LocalDate dateToday = LocalDate.now();
		
		List<String> statusList = new ArrayList<String>();
		statusList.add("booked");
		
		List<RestaurantDetails> rsDetailsList = restaurantDetailsRepository.findByTableSeatGreaterThanEqualAndSmokingAndRestaurant(uvs.getRestaurantDetails().getTableSeat(), uvs.getRestaurantDetails().isSmoking(), uvs.getRestaurantDetails().getRestaurant());
		
		List<UserEvents> uvsList = userEventsRepository.findByRequestTimeAndStatusTableInAndRestaurantDetailsInOrderByUsersAsc(dateToday, statusList, rsDetailsList);
		
		if(uvsList.size() > 0){
			uvsList.get(0).setStatusTable("ready");
			userEventsRepository.save(uvsList.get(0));
		}
		
		List<UserEvents> latestUserEvents = getUserList(uvs.getRestaurantDetails().getRestaurant().getId());
		
		return latestUserEvents;
	}
	
	@RequestMapping(value="/informUserNoti", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserEvents> informUserNoti() {
		return null;
	}

	@RequestMapping(value="/informUserNoti/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> informUserNotiById(@PathVariable Long id) throws IOException {//throws TwilioRestException {
		
		UserEvents uvs = userEventsRepository.findOne(id);
		Boolean result = pushNotificationService.sendNotification(uvs.getUsers().getGcmId());
		
		return ResponseEntity.accepted().body(result);
	}

	@RequestMapping(value="/informUserSms", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserEvents> informUserSms() {
		return null;
	}

	@RequestMapping(value="/informUserSms/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> informUserSmsById(@PathVariable Long id) throws TwilioRestException {//throws TwilioRestException {
		
		UserEvents uvs = userEventsRepository.findOne(id);
		Boolean result = smsService.sendSms(uvs.getUsers().getPhoneNumber());
		
		return ResponseEntity.accepted().body(result);
	}
	
	@RequestMapping(value="/getRsInfo", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserEvents> getRsInfo() {
		return null;
	}

	@RequestMapping(value="/getRsInfo/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> getRsInfoById(@PathVariable Long id){
		
		Restaurant rs = restaurantRepository.findOne(id);
		
		return ResponseEntity.accepted().body(rs);
	}
}

