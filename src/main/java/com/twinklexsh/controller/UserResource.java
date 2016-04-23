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
public class UserResource {
	
	@Inject
	UsersRepository userRepository;
	
	
	@Inject
	UserEventsRepository userEventsRepository;
	
	@Inject
	RestaurantDetailsRepository restaurantDetailsRepository;
	
	@Inject
	RestaurantRepository restaurantRepository;
	
	@RequestMapping(value="/register", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> registerUser(@RequestBody Users account ) {
		
		List<Users> extus = userRepository.findAllByUsername(account.getUsername());
		
		if(extus.size() > 0){
			return ResponseEntity.badRequest().header("Failure", "This username is already registed").body(null);
		}

		account.setUserRole(99);
		if (account.getPhoneNumber().startsWith("01")){
			account.setPhoneNumber("+6" + account.getPhoneNumber());
		}
		
		if(account.getPhoneNumber().startsWith("601")){
			account.setPhoneNumber("+" + account.getPhoneNumber());
		}

		Users us = userRepository.save(account);

		return ResponseEntity.accepted().body(us);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> loginUser(@RequestBody Users account ) {
		
		Users us = userRepository.findByUsernameAndUserPassword(account.getUsername(), account.getUserPassword());
		
		if(us!=null){
			us.setUserPassword("");
			return ResponseEntity.accepted().body(us);
		}
		
		return ResponseEntity.badRequest().header("Failure", "Failed to Authorize").body(null);
		
		
	}
	


}
