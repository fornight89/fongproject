package com.twinklexsh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twinklexsh.domain.Restaurant;
import com.twinklexsh.domain.RestaurantDetails;

public interface RestaurantDetailsRepository extends JpaRepository<RestaurantDetails, Long> {

	List<RestaurantDetails> findByRestaurant(Restaurant rs);

	//List<RestaurantDetails> findByTableSeatAndSmokingAndRestaurant(int tableSeat, boolean smoking, Restaurant rs);

	//List<RestaurantDetails> findByTableSeatLessThanEqualAndSmokingAndRestaurant(int tableSeat, boolean smoking,	Restaurant rs);

	List<RestaurantDetails> findByTableSeatGreaterThanEqualAndSmokingAndRestaurant(int tableSeat, boolean smoking,
			Restaurant rs);

}
