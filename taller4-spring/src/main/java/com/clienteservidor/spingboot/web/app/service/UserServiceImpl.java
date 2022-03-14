package com.clienteservidor.spingboot.web.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clienteservidor.spingboot.web.app.entity.User;
import com.clienteservidor.spingboot.web.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository repository;
	
	@Override
	public Iterable<User> getAllUsers() {
		return repository.findAll();
	}

}
