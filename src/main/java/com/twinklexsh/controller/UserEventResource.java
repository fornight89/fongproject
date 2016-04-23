package com.twinklexsh.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.twinklexsh.domain.Restaurant;
import com.twinklexsh.domain.RestaurantDetails;
import com.twinklexsh.domain.Result;
import com.twinklexsh.domain.UserEvents;
import com.twinklexsh.domain.UserRequest;
import com.twinklexsh.domain.Users;
import com.twinklexsh.repository.RestaurantDetailsRepository;
import com.twinklexsh.repository.RestaurantRepository;
import com.twinklexsh.repository.UserEventsRepository;
import com.twinklexsh.repository.UsersRepository;

import javax.inject.Inject;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class UserEventResource {
	
	@Inject
	UsersRepository userRepository;
	
	
	@Inject
	UserEventsRepository userEventsRepository;
	
	@Inject
	RestaurantDetailsRepository restaurantDetailsRepository;
	
	@Inject
	RestaurantRepository restaurantRepository;
	
	@RequestMapping(value="/getUserEvents", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> returnUsers() {
		Users users = userRepository.findByUsername("admin");
		return ResponseEntity.accepted().body(users);
	}
	
	@RequestMapping(value="/getUserEvents/{uId}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result> getUserEventsByTodayAndUserId(@PathVariable Long uId) {
		Users user = userRepository.findOne(uId);

		LocalDate dateToday = LocalDate.now();
		
		List<String> statusList = new ArrayList<String>();
		statusList.add("booked");
		statusList.add("servicing");
		statusList.add("ready");
		
		//List<UserEvents> us = userEventsRepository.findByUsersAndStatusTableInAndRequestTime(user, statusList, dateToday);
		UserEvents us = userEventsRepository.findByUsersAndStatusTableInAndRequestTime(user, statusList, dateToday);
		
		Result result = null;
		if(us!=null){
			List<RestaurantDetails> rsDetails = restaurantDetailsRepository.findByTableSeatGreaterThanEqualAndSmokingAndRestaurant(us.getRestaurantDetails().getTableSeat(), false, us.getRestaurantDetails().getRestaurant());
			List<UserEvents> uvs = userEventsRepository.findByRequestTimeAndStatusTableInAndRestaurantDetailsInAndUsersNotAndIdLessThanOrderByEstimateTimeAsc(dateToday, statusList, rsDetails, user, uId);
			System.out.println("-------------" + uvs.size() + " - " + rsDetails.size());
			int availableSeat = uvs.size() - rsDetails.size();
			Long estimateTime = (long) (availableSeat * 15);
			UserRequest userRequest = new UserRequest();
			userRequest.setUserid(uId.toString());
			userRequest.setBabychair(false); //hardcode alwasys false / no column for this request;
			userRequest.setTableSeat(us.getRestaurantDetails().getTableSeat());
			userRequest.setSmoking(false);
			userRequest.setRestaurantid(us.getRestaurantDetails().getRestaurant().getId().toString());
			result = new Result(userRequest, us, availableSeat, estimateTime);
			
		}
		
		return ResponseEntity.accepted().body(result);
	}
	
//	@RequestMapping(value="/getUserEvents/{uId}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//    public Boolean getUserEventsByTodayAndUserId(@PathVariable Long uId) {
//		Users user = userRepository.findOne(uId);
//		
//		
//		LocalDate dateToday = LocalDate.now();
//		
//		List<String> statusList = new ArrayList<String>();
//		statusList.add("booked");
//		statusList.add("servicing");
//		statusList.add("ready");
//		
//		List<UserEvents> us = userEventsRepository.findByUsersAndStatusTableInAndRequestTime(user, statusList, dateToday);
//		
//		System.out.println(user.getId());
//
//		if(us.size()>0){
//			System.out.println(us.size());
//			return true;  //true = already booked some events. 
//		}
//		
//		System.out.println(us.size());
//		
//		return false;
//		
//	}
	
	@RequestMapping(value="/registerUserEvents", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result> registerEvents(@RequestBody UserRequest userRequest) {
		
		
		//based on user requirement - find the restarunt table that available now. 
		
		//1) find the RESTAURANT  based on user request. 
		Restaurant rs = restaurantRepository.findOne(Long.parseLong(userRequest.getRestaurantid()));
		Users us = userRepository.findOne(Long.parseLong(userRequest.getUserid())); 
		
		//2) find the RESTAURANT DETAILS BASED ON THE RESTAURANT FOUND ON STEP 1 
		List<RestaurantDetails> rsDetails = restaurantDetailsRepository.findByTableSeatGreaterThanEqualAndSmokingAndRestaurant(userRequest.getTableSeat(), userRequest.isSmoking(), rs);
		System.out.println("registerEvents() - Total restartaurant Details found that able match with user request : " + rsDetails.size());
		
		//3) GET THE CURRENT DATE '2015-04-01'
		LocalDate dateToday = LocalDate.now();
		//	LocalDateTime datetime = LocalDateTime.now();
		
		//4) GET THE CURRENT SERVICE LIST. 
		List<String> statusList = new ArrayList<String>();
		statusList.add("booked");
		statusList.add("servicing");
		statusList.add("ready");
		
		//5) FIND THE AVAILABLE TABLE BASED ON USER REQUEST. 
		List<UserEvents> uvs = userEventsRepository.findByRequestTimeAndStatusTableInAndRestaurantDetailsInOrderByEstimateTimeAsc(dateToday, statusList, rsDetails);
		System.out.println("registerEvents() - There have number of table : " + uvs.size() + " are in booked, servicing, ready status. ");
		
		UserEvents usEventsRegisterResult = new UserEvents();
		usEventsRegisterResult.setRequestTime(dateToday);
		usEventsRegisterResult.setRestaurantDetails(rsDetails.get(0));
		usEventsRegisterResult.setUsers(us);
		//usEventsRegisterResult.setEstimateTime(datetime);
		
		//6) availableSeat = to check is there any available seat for now. if negative which mean you seat is okay now.
		int availableSeat = uvs.size() - rsDetails.size();
		System.out.println("registerEvents() - avaiableSeat (Person in Next)  : " + availableSeat );
		
		if(availableSeat >= 0){
			// U HAVE TO REQUEST IS PENDING...RESTAURANT IS BUSY NOW....PLEASE WAIT FOR A Available Seat.
			usEventsRegisterResult.setStatusTable("booked");
		}else{
			// U HAVE TO REQUEST IS READY NOW..PLS GO TO COUNTER.
			usEventsRegisterResult.setStatusTable("ready");
		}
		
		UserEvents usEventRegisterResult = userEventsRepository.save(usEventsRegisterResult);
		
		//hardcoded - each person 15mins. 
		Long estimateTime = (long) (availableSeat * 15);
		
		Result result = new Result(userRequest, usEventRegisterResult, availableSeat, estimateTime);
		return ResponseEntity.accepted().body(result);
		
		
	}
	
	@RequestMapping(value="/cancelUserEvents", method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> cancelEvents() {
		return null;
	}
	
	@RequestMapping(value="/cancelUserEvents/{userEventsId}", method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> cancelEventsById(@PathVariable Long userEventsId) {
	//public ResponseEntity<Result> cancelEvents(@RequestBody Result userRequest) {
		
		UserEvents uvs = userEventsRepository.findOne(userEventsId);
		//UserEvents uvs = userEventsRepository.findOne(userRequest.getUserEvents().getId());
		
		uvs.setStatusTable("cancel");
		
		userEventsRepository.save(uvs);
		
		return null;
	}

}
