package com.clienteservidor.spingboot.web.app.service;

import com.clienteservidor.spingboot.web.app.entity.User;

public interface UserService {
	
	public Iterable<User> getAllUsers();

}
