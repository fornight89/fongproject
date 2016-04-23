package com.twinklexsh.repository;

import java.util.List;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.twinklexsh.domain.RestaurantDetails;
import com.twinklexsh.domain.UserEvents;
import com.twinklexsh.domain.Users;

public interface UserEventsRepository  extends JpaRepository<UserEvents, Long> {

	
	//List<UserEvents> findByRestaurantAndUsers(@Param("empID")Long id, @Param("empID") Long id2, @Param("empID") String string);

	//List<UserEvents> findByUser(Long id, String string);

	List<UserEvents> findByUsersAndStatusTableAndRequestTime(Users user, String status, LocalDate requestTime);

	List<UserEvents> findByRequestTimeAndStatusTableInAndRestaurantDetailsIn(LocalDate dateToday,
			List<String> statusList, List<RestaurantDetails> rsDetails);

	//List<UserEvents> findByUsersAndStatusTableInAndRequestTime(Users user, List<String> statusList, LocalDate dateToday);
	UserEvents findByUsersAndStatusTableInAndRequestTime(Users user, List<String> statusList, LocalDate dateToday);
	
	List<UserEvents> findByRequestTimeAndStatusTableInAndRestaurantDetailsInOrderByEstimateTimeAsc(LocalDate dateToday,
			List<String> statusList, List<RestaurantDetails> rsDetails);
	
	List<UserEvents> findByRequestTimeAndStatusTableInAndRestaurantDetailsInAndUsersNotAndIdLessThanOrderByEstimateTimeAsc(LocalDate dateToday,
			List<String> statusList, List<RestaurantDetails> rsDetails, Users user, Long uid);

	List<UserEvents> findByRequestTimeAndStatusTableInAndRestaurantDetailsInOrderByUsersAsc(LocalDate dateToday,
			List<String> statusList, List<RestaurantDetails> rsDetailsList);

}
