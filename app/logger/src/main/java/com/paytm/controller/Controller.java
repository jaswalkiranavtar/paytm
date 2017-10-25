package com.paytm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paytm.pojo.MyEvent;
import com.paytm.repository.MyEventRepository;

@RestController
@RequestMapping(path = "/event")
public class Controller {
	
	@Autowired
	MyEventRepository repository;
	
    @GetMapping
    public Iterable<MyEvent> listAll(@Param(value = "user") String user) {
    	return repository.findByUser(user);
    }
    
    @PostMapping(consumes = "application/json")
    public MyEvent saveAnEvent(@RequestBody MyEvent myEvent) {
    	return repository.save(myEvent);
    }
    
}
