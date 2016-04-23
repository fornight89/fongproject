package com.twinklexsh.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="restaurantdetails")
public class RestaurantDetails implements Serializable{

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="tableseat")
	private int tableSeat;
	
	@Column(name="smoking")
	private boolean smoking;
	
	@ManyToOne
	@JoinColumn(name="restaurant_id")
	private Restaurant restaurant;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTableSeat() {
		return tableSeat;
	}

	public void setTableSeat(int tableSeat) {
		this.tableSeat = tableSeat;
	}

	public boolean isSmoking() {
		return smoking;
	}

	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "RestaurantDetails [id=" + id + ", tableSeat=" + tableSeat + ", smoking=" + smoking + ", restaurant="
				+ restaurant + "]";
	}
	
	
	
	
}
