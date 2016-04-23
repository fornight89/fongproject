package com.twinklexsh.controller;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.twinklexsh.domain.Restaurant;
import com.twinklexsh.domain.RestaurantTable;
import com.twinklexsh.domain.Result;
import com.twinklexsh.domain.UserPlan;
import com.twinklexsh.domain.Users;
import com.twinklexsh.repository.RestaurantRepository;
import com.twinklexsh.repository.UsersRepository;

import javax.inject.Inject;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ProductResource {
	
	@Inject
	RestaurantRepository restaurantRepository;

	@RequestMapping(value="/getProductList", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getRestaurantList() {
		System.out.println("getRestaurantList()");
		
		List<Restaurant> rs = restaurantRepository.findAll();
		System.out.println(rs.size());
		for (int i =0 ; i < rs.size(); i++){
			System.out.println(rs.get(i).getHours());
		}
		return rs;
	}

}
