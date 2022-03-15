package com.clienteservidor.spingboot.web.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clienteservidor.spingboot.web.app.dto.ChangePasswordForm;
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
		if(user.getConfirmPassword()==null || user.getConfirmPassword().isEmpty()) {
			throw new Exception("ConfirmPassword es obligatorio");
		}
		
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

	@Override
	public User getUserById(Long id) throws Exception {
		return repository.findById(id).orElseThrow(() -> new Exception("El usuario no existe!"));
	}

	@Override
	public User updateUser(User fromUser) throws Exception {
		User toUser = getUserById(fromUser.getId());
		mapUser(fromUser, toUser);
		return repository.save(toUser);
		
	}
	
	/**
	 * Map everything but the password.
	 * @param from
	 * @param to
	 */
	
	protected void mapUser(User from, User to) {
		to.setUsername(from.getUsername());
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
		to.setEmail(from.getEmail());
		to.setRoles(from.getRoles());
	}

	@Override
	public void deleteUser(Long id) throws Exception {
		User user = getUserById(id);
		
		repository.delete(user);
	}

	@Override
	public User changePassword(ChangePasswordForm form) throws Exception {
		User user = getUserById(form.getId());
		
		if(!user.getPassword().equals(form.getCurrentPassword())) {
			throw new Exception("Current password invalido");
		}
		if(user.getPassword().equals(form.getNewPassword())) {
			throw new Exception("El password nuevo debe ser diferente al actual");
		}
		if(!form.getNewPassword().equals(form.getConfirmPassword())) {
			throw new Exception ("Confirm password Incorrecto");
		}
		
		user.setPassword(form.getNewPassword());
		
		return repository.save(user);
	
	}

}
