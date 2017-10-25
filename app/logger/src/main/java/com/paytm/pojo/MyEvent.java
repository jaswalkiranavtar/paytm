package com.paytm.pojo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MyEvent extends Event{

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return super.getId();
    }
	
	@Override
	public EventType getEventType() {
		return super.getEventType();
	}
	
	@Override
	public String getUser() {
		return super.getUser();
	}
    
	@Override
	public Outcome getOutcome() {
		return super.getOutcome();
	}
	
	@Override
	public String getOperand() {
		return super.getOperand();
	}
	
	@Override
	public LocalDateTime getCreatedDateTime() {
		return super.getCreatedDateTime();
	}

}
