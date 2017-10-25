package com.paytm.pojo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Event {

    private long id;
    private String user;
    private EventType eventType;
    private Outcome outcome;
    private String operand;
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private LocalDateTime createdDateTime;
	
    public Event() {
    	
    }
    
    public Event(String user) {
    	this();
    	this.user = user;
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

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
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

	public Outcome getOutcome() {
		return outcome;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}
}
