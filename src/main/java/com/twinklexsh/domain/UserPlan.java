package com.twinklexsh.domain;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class UserPlan {

	long id;
	int person;
	LocalTime requestTime;
	LocalDate requestDate;
	String status;
	String gcmID;
	int requestNumber;

	public UserPlan(long id, int person, LocalTime requestTime, LocalDate requestDate, String status, String gcmID, int requestNumber) {
		super();
		this.id = id;
		this.person = person;
		this.requestTime = requestTime;
		this.requestDate = requestDate;
		this.status = status;
		this.gcmID = gcmID;
		this.requestNumber = requestNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPerson() {
		return person;
	}
	public void setPerson(int person) {
		this.person = person;
	}
	public LocalTime getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(LocalTime requestTime) {
		this.requestTime = requestTime;
	}
	public LocalDate getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGcmID() {
		return gcmID;
	}
	public void setGcmID(String gcmID) {
		this.gcmID = gcmID;
	}
	public int getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(int requestNumber) {
		this.requestNumber = requestNumber;
	}
	
	
}
