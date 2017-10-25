package com.gaurav.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gaurav.pojo.MyEvent;

@Repository
public interface MyEventRepository extends CrudRepository<MyEvent, Long> {
	
}
