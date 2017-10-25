package com.gaurav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaurav.pojo.MyEvent;
import com.gaurav.repository.MyEventRepository;

@RestController
@RequestMapping(path = "/event")
public class Controller {
	
	@Autowired
	MyEventRepository repository;
	
    @GetMapping
    public Iterable<MyEvent> listAll() {
    	return repository.findAll();
    }
    
    @PostMapping(consumes = "application/json")
    public MyEvent saveAnEvent(@RequestBody MyEvent myEvent) {
    	return repository.save(myEvent);
    }
    
}
