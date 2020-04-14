package com.snowball.microservice.account.controller;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snowball.microservice.account.model.User;


@RestController
@RequestMapping("/account")
public class UserController extends controller<User> {
	
	@GetMapping("/{username}")
	public ResponseEntity<?> get(@RequestParam String username){
		
		return ResponseEntity.ok(service.user.getByUserName(username));
	}
	@Override
	public ResponseEntity<?> get(@RequestParam ObjectId id) {
		// TODO Auto-generated method stub
		//kafka.sendMessage(service.user.get(id).orElse(new User()));
		return ResponseEntity.ok(service.user.get(id));
	}
	@Override
	public ResponseEntity<?> post(@RequestBody User data) {
		// TODO Auto-generated method stub
		return ResponseEntity.status(HttpStatus.CREATED).body(service.user.post(data));
	}
	@Override
	public ResponseEntity<?> put(@RequestBody User data,@RequestParam ObjectId id) {
		// TODO Auto-generated method stub
		
		return ResponseEntity.ok(service.user.put(data, id));
	}
	@Override
	public ResponseEntity<?> delete(@RequestParam ObjectId id) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(service.user.delete(id));
	}
	@Override
	public ResponseEntity<?> list() {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(service.user.list());
	}
	
}
