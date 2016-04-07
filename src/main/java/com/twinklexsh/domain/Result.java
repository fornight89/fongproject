package com.twinklexsh.domain;

public class Result {
	int inNextPerson;
	int currentNumber;
	int requestNumber;
	
	
	public Result(int inNextPerson, int currentNumber, int requestNumber) {
		super();
		this.inNextPerson = inNextPerson;
		this.currentNumber = currentNumber;
		this.requestNumber = requestNumber;
	}
	
	public int getInNextPerson() {
		return inNextPerson;
	}
	public void setInNextPerson(int inNextPerson) {
		this.inNextPerson = inNextPerson;
	}
	public int getCurrentNumber() {
		return currentNumber;
	}
	public void setCurrentNumber(int currentNumber) {
		this.currentNumber = currentNumber;
	}
	public int getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(int requestNumber) {
		this.requestNumber = requestNumber;
	}
	
	
}
