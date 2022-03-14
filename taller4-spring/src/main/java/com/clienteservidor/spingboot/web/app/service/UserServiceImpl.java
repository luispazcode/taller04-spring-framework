package com.clienteservidor.spingboot.web.app.service;

import java.util.Optional;

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
	
	private boolean checkUsernameAvailable(User user) throws Exception{
		Optional<User> userFound = repository.findByUsername(user.getUsername());
		if(userFound.isPresent()) {
			throw new Exception("Username no disponible");
		}
		return true;
	}
	
	private boolean checkPasswordValid(User user) throws Exception {
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			throw new Exception("Password y ConfirmPassword no son iguales");
		}
		return true;
	}

	@Override
	public User createUser(User user) throws Exception {
		if(checkUsernameAvailable(user) && checkPasswordValid(user)) {
			user = repository.save(user);
		}
		return user;
	}

}
