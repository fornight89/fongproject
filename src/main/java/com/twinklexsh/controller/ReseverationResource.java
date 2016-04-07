package com.twinklexsh.controller;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.twinklexsh.domain.Message;
import com.twinklexsh.domain.RestaurantTable;
import com.twinklexsh.domain.Result;
import com.twinklexsh.domain.UserPlan;;

@RestController
@RequestMapping("/api")
public class ReseverationResource {
	
	@RequestMapping(value="/reserve", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Result> message() {
 
		RestaurantTable rt1 = new RestaurantTable(1, 2, "starbucks");
		RestaurantTable rt2 = new RestaurantTable(2, 2, "starbucks");
		RestaurantTable rt3 = new RestaurantTable(3, 6, "starbucks");
		RestaurantTable rt4 = new RestaurantTable(4, 6, "starbucks");
		
		List<RestaurantTable> lt = new ArrayList<RestaurantTable>();
		lt.add(rt1);
		lt.add(rt2);
		lt.add(rt3);
		lt.add(rt4);
		
		int person = 2;
		String status = "booked";
		String gcmID = "";
		LocalDate date = new LocalDate(2004, 12, 25);
		LocalTime time = new LocalTime(12, 20);
		int requestNumber = 301; 
		
		UserPlan up1 = new UserPlan(1, 2, time, date, "activated", "", 295);
		UserPlan up2 = new UserPlan(2, 2, time, date, "activated", "", 296);
		UserPlan up3 = new UserPlan(3, 4, time, date, "booked", "", 299);
		UserPlan up4 = new UserPlan(4, 4, time, date, "booked", "", 300);
		UserPlan up5 = new UserPlan(5, 6, time, date, "activated", "", 297);
		UserPlan up6 = new UserPlan(6, 6, time, date, "activated", "", 298);
		
		List<UserPlan> listOfUP = new ArrayList<UserPlan>();
		listOfUP.add(up1);
		listOfUP.add(up2);
		listOfUP.add(up3);
		listOfUP.add(up4);
		listOfUP.add(up5);
		listOfUP.add(up6);
		
		//----------------------------------------------------------------------------
		//return number of booked = 2 calculating 
		//return request number ( system generated ) 301
		//return latest number = 297 
		
		int inNextPerson =2;
		int currentNumber = 296;
		
		
		Result rs = new Result(inNextPerson, currentNumber, requestNumber);
		List<Result> result = new ArrayList<Result>();
		result.add(rs);
		
        return result;
    }
	
	@RequestMapping(value="/getPin", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result> returnObj() {
 
		RestaurantTable rt1 = new RestaurantTable(1, 2, "starbucks");
		RestaurantTable rt2 = new RestaurantTable(2, 2, "starbucks");
		RestaurantTable rt3 = new RestaurantTable(3, 6, "starbucks");
		RestaurantTable rt4 = new RestaurantTable(4, 6, "starbucks");
		
		List<RestaurantTable> lt = new ArrayList<RestaurantTable>();
		lt.add(rt1);
		lt.add(rt2);
		lt.add(rt3);
		lt.add(rt4);
		
		int person = 2;
		String status = "booked";
		String gcmID = "";
		LocalDate date = new LocalDate(2004, 12, 25);
		LocalTime time = new LocalTime(12, 20);
		int requestNumber = 301; 
		
		UserPlan up1 = new UserPlan(1, 2, time, date, "activated", "", 295);
		UserPlan up2 = new UserPlan(2, 2, time, date, "activated", "", 296);
		UserPlan up3 = new UserPlan(3, 4, time, date, "booked", "", 299);
		UserPlan up4 = new UserPlan(4, 4, time, date, "booked", "", 300);
		UserPlan up5 = new UserPlan(5, 6, time, date, "activated", "", 297);
		UserPlan up6 = new UserPlan(6, 6, time, date, "activated", "", 298);
		
		List<UserPlan> listOfUP = new ArrayList<UserPlan>();
		listOfUP.add(up1);
		listOfUP.add(up2);
		listOfUP.add(up3);
		listOfUP.add(up4);
		listOfUP.add(up5);
		listOfUP.add(up6);
		
		//----------------------------------------------------------------------------
		//return number of booked = 2 calculating 
		//return request number ( system generated ) 301
		//return latest number = 297 
		
		int inNextPerson =2;
		int currentNumber = 296;
		
		
		Result rs = new Result(inNextPerson, currentNumber, requestNumber);
		
        return ResponseEntity.accepted().body(rs);
    }

}
