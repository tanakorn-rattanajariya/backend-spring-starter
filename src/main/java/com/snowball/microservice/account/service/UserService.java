package com.snowball.microservice.account.service;
import org.springframework.stereotype.Service;
import com.snowball.microservice.account.model.User;
import com.snowball.microservice.account.repository.UserRepository;

@Service
public class UserService extends service<User,UserRepository> {
	public User getByUserName(String username) {
		User t = repository.findByUserName(username);
		kafka.get().send(application.concat("-getUsername-").concat(t.getClass().getSimpleName()),t);
		return t;
	}
}
