package com.bideeparts.website.gallery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bideeparts.website.gallery.controllers.services.UserService;
import com.bideeparts.website.gallery.model.User;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> getUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@GetMapping
	@RequestMapping("{id}")
	public User find(@PathVariable String id) {
		User user = userService.getUser(id);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
		}
		return user;
	}
	
	@DeleteMapping(value="{id}")
	public void delete(@PathVariable String id) {
		User user = userService.getUser(id);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
		}
		userService.deleteUser(id);
	}
	
	@PutMapping(value="{id}")
	public User update(@PathVariable String id, @RequestBody User user) {
		User savedUser = userService.updateUser(id, user);
		if (savedUser == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id); 
		}
		return savedUser;
	}

}
