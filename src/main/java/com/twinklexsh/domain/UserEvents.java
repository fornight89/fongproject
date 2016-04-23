package com.twinklexsh.domain;

import java.io.Serializable;

import javax.persistence.*;

import com.twinklexsh.domain.util.LocalDatePersistenceConverter;
import com.twinklexsh.domain.util.LocalDateTimeAttributeConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="userevents")
public class UserEvents implements Serializable{
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="request_time")
	@Convert(converter = LocalDatePersistenceConverter.class)
	private LocalDate requestTime;
	
	@Column(name="status_table")
	private String statusTable;
	
	@ManyToOne
	@JoinColumn(name="restraurantdetails_id")
	private RestaurantDetails restaurantDetails;
	
	@ManyToOne
	@JoinColumn(name="username_id")
	private Users users;
	
	@Column(name="est_time")
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime estimateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(LocalDate requestTime) {
		this.requestTime = requestTime;
	}

	public String getStatusTable() {
		return statusTable;
	}

	public void setStatusTable(String statusTable) {
		this.statusTable = statusTable;
	}

	public RestaurantDetails getRestaurantDetails() {
		return restaurantDetails;
	}

	public void setRestaurantDetails(RestaurantDetails restaurantDetails) {
		this.restaurantDetails = restaurantDetails;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public LocalDateTime getEstimateTime() {
		return estimateTime;
	}

	public void setEstimateTime(LocalDateTime estimateTime) {
		this.estimateTime = estimateTime;
	}
	
	

}
