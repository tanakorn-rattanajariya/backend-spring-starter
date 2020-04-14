package com.snowball.microservice.account.controller;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snowball.microservice.account.service.service;

@RestController
public abstract class controller<T> {
	protected final Logger log = LoggerFactory.getLogger(controller.class);
	@Autowired
	protected service service;
	
	@GetMapping("/{id}")
	public abstract ResponseEntity<?> get(ObjectId id);
	@GetMapping()
	public abstract ResponseEntity<?> list();
	@PostMapping()
	public abstract ResponseEntity<?> post(T data);
	@PutMapping("/{id}")
	public abstract ResponseEntity<?> put(T data,ObjectId id);
	@DeleteMapping("/{id}")
	public abstract ResponseEntity<?> delete(ObjectId id);
}
