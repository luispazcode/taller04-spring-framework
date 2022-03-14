package com.clienteservidor.spingboot.web.app.service;

import javax.validation.Valid;

import com.clienteservidor.spingboot.web.app.entity.User;

public interface UserService {
	
	public Iterable<User> getAllUsers();

	public User createUser( User user) throws Exception;
	
	public User getUserById(Long id) throws Exception;
	
	public User updateUser(User user) throws Exception;

}
