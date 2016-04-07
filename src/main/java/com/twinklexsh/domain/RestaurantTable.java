package com.twinklexsh.domain;

public class RestaurantTable {
	
	Long id;
	int numberOfSeat;
	String restarantName;
	
	public RestaurantTable(long i, int numberOfSeat, String restarantName) {
		super();
		this.id = i;
		this.numberOfSeat = numberOfSeat;
		this.restarantName = restarantName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumberOfSeat() {
		return numberOfSeat;
	}

	public void setNumberOfSeat(int numberOfSeat) {
		this.numberOfSeat = numberOfSeat;
	}

	public String getRestarantName() {
		return restarantName;
	}

	public void setRestarantName(String restarantName) {
		this.restarantName = restarantName;
	}

}
