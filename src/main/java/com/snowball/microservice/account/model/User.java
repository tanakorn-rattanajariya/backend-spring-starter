package com.snowball.microservice.account.model;



import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;

import lombok.Data;
import lombok.NoArgsConstructor;
@Document(value = "user")
@Data
@NoArgsConstructor
@RedisHash("user")
public class User {
	@NotNull
	private String name;
	@Id
	private String id;
	@NotNull
	private String username;
	@NotNull
	private String password;
}
