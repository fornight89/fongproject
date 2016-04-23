package com.twinklexsh.domain;

import java.io.Serializable;

public class Result implements Serializable{
	
	private UserRequest userRequest;
	private UserEvents userEvents;
	private int inNextPerson;
	private Long estimateTime;
	
	public UserRequest getUserRequest() {
		return userRequest;
	}
	public void setUserRequest(UserRequest userRequest) {
		this.userRequest = userRequest;
	}
	public UserEvents getUserEvents() {
		return userEvents;
	}
	public void setUserEvents(UserEvents userEvents) {
		this.userEvents = userEvents;
	}
	public int getInNextPerson() {
		return inNextPerson;
	}
	public void setInNextPerson(int inNextPerson) {
		this.inNextPerson = inNextPerson;
	}
	public Long getEstimateTime() {
		return estimateTime;
	}
	public void setEstimateTime(Long estimateTime) {
		this.estimateTime = estimateTime;
	}
	
	public Result(UserRequest userRequest, UserEvents userEvents, int inNextPerson, Long estimateTime) {
		super();
		this.userRequest = userRequest;
		this.userEvents = userEvents;
		this.inNextPerson = inNextPerson;
		this.estimateTime = estimateTime;
	}

	
	
	
}
