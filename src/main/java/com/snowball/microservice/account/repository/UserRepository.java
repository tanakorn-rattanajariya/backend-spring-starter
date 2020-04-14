package com.snowball.microservice.account.repository;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;

import com.snowball.microservice.account.model.User;

@EnableRedisRepositories
@Repository
public interface UserRepository extends MongoRepository<User,ObjectId> {
	@Query("{username : ?0}")
	public User findByUserName(String username);
}
