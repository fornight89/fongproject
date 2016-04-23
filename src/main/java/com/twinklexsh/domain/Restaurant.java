package com.twinklexsh.domain;

import javax.persistence.*;

@Entity
@Table(name="restaurant")
public class Restaurant {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="restaurant_name")
	private String restaurantName;
	
	@Column(name="hours")
	private String hours;
	
	@Column(name="location")
	private String location;
	
	@Column(name="price")
	private String price;
	
	@Column(name="image")
	private String image;
	
	@Column(name="image_item")
	private String imageItem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImageItem() {
		return imageItem;
	}

	public void setImageItem(String imageItem) {
		this.imageItem = imageItem;
	}
	
	
}
