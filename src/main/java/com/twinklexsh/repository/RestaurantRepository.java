package com.twinklexsh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twinklexsh.domain.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
