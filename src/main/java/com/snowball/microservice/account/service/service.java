package com.snowball.microservice.account.service;


import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.snowball.microservice.account.config.KafkaProducerConfig;
import com.snowball.microservice.account.model.User;


@Service
public abstract class service<T,R extends MongoRepository<T,ObjectId>> {
	protected final Logger log = LoggerFactory.getLogger(service.class);	
	@Autowired
	public UserService user;
	
	
	@Autowired
	protected R repository;
	@Autowired
	protected KafkaProducerConfig<T> kafka;
	@Value("${spring.application.name}")
	protected String application;
	
	
	public static String[] getNullPropertyNames (Object source) {
	    final BeanWrapper src = new BeanWrapperImpl(source);
	    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

	    Set<String> emptyNames = new HashSet<String>();
	    for(java.beans.PropertyDescriptor pd : pds) {
	        Object srcValue = src.getPropertyValue(pd.getName());
	        if (srcValue == null) emptyNames.add(pd.getName());
	    }

	    String[] result = new String[emptyNames.size()];
	    return emptyNames.toArray(result);
	}
	public List<T> list() {
		List<T> t = repository.findAll();
		if (!t.isEmpty()) {
			kafka.list().send(application.concat("-list-").concat(t.get(0).getClass().getSimpleName()),t);
		}
		return t;
	}
	public Optional<T> get(ObjectId id) {
		Optional<T> t = repository.findById(id);
		if (t.isPresent()) {
			kafka.get().send(application.concat("-get-").concat(t.get().getClass().getSimpleName()),t.get());
			return t;
		}
		throw new NoSuchElementException();
	}
	public T post(T data) {
		T t = repository.save(data);
		kafka.get().send(application.concat("-post-").concat(t.getClass().getSimpleName()),t);
		return t;
	}
	public T put(T data,ObjectId id) {
		Optional<T> dat = repository.findById(id);
		if (dat.isPresent()) {
			T t = dat.get();
			BeanUtils.copyProperties(data, t,getNullPropertyNames(data));
			kafka.get().send(application.concat("-put-").concat(t.getClass().getSimpleName()),t);
			return repository.save(t);
		}
		throw new NoSuchElementException();
	}
	public ObjectId delete(ObjectId id) {
		Optional<T> t = repository.findById(id);
		if (t.isPresent()) {
			kafka.get().send(application.concat("-delete-").concat(t.getClass().getSimpleName()),t.get());
			repository.deleteById(id);
			return id;
		}
		throw new NoSuchElementException();
	}
}
