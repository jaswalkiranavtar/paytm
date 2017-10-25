package com.paytm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paytm.pojo.MyEvent;

@Repository
public interface MyEventRepository extends CrudRepository<MyEvent, Long> {
	
	Iterable<MyEvent> findByUser(String user);
	
}
