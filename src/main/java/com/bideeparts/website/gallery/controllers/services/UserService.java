package com.bideeparts.website.gallery.controllers.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bideeparts.website.gallery.model.User;
import com.bideeparts.website.gallery.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User createUser(User user) {
		user.setId(UniqueIdGenerator.getRandomId());
		Date date = new Date();
		user.setCreatedOn(date);
		user.setUpdatedOn(date);
		return userRepository.saveAndFlush(user);
	}
	
	public User getUser(String id) {
		Optional<User> user =  userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}
	
	public User updateUser(String id, User user) {
		User savedUser = getUser(id);
		if (savedUser == null) {
			return null;
		}
		
		savedUser.setEmail(user.getEmail());
		savedUser.setPassword(user.getPassword());
		savedUser.setUsername(user.getPassword());
		savedUser.setId(id);
		savedUser.setUpdatedOn(new Date());
		return userRepository.saveAndFlush(savedUser);
	}

	public void deleteUser(String id) {
		userRepository.deleteById(id);
	}
}
