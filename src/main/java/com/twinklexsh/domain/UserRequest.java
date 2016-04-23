package com.twinklexsh.domain;

public class UserRequest {

	private int tableSeat;
	private boolean babychair;
	private boolean smoking;
	private String restaurantid;
	private String userid;
	
	public int getTableSeat() {
		return tableSeat;
	}
	public void setTableSeat(int tableSeat) {
		this.tableSeat = tableSeat;
	}
	public boolean isBabychair() {
		return babychair;
	}
	public void setBabychair(boolean babychair) {
		this.babychair = babychair;
	}
	public boolean isSmoking() {
		return smoking;
	}
	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}
	public String getRestaurantid() {
		return restaurantid;
	}
	public void setRestaurantid(String restaurantid) {
		this.restaurantid = restaurantid;
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Override
	public String toString() {
		return "UserRequest [tableSeat=" + tableSeat + ", babychair=" + babychair + ", smoking=" + smoking
				+ ", restaurantid=" + restaurantid + "]";
	}
	
}

