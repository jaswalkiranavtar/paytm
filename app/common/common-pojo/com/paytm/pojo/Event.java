package com.paytm.pojo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Event {

    private long id;
    private String user;
    private String eventType;
    private String outcome;
    private String operand;
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private LocalDateTime createdDateTime;
	
    public Event() {
    	
    }
    
    public Event(String user, String eventType, String outcome) {
    	this.user = user;
    	this.eventType = eventType;
    	this.outcome = outcome;
    	this.createdDateTime = LocalDateTime.now();
    }
    
    public String toString() {
    	return "User:" + this.user + 
    			" Id:" + this.id + 
    			" EventType:" + this.eventType + 
    			" Outcome:" + this.outcome + 
    			" CreatedDateTime:" + this.createdDateTime;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getOperand() {
		return operand;
	}

	public void setOperand(String operand) {
		this.operand = operand;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
}
