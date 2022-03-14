package com.clienteservidor.spingboot.web.app.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clienteservidor.spingboot.web.app.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	//public Set<User> findByUsername(String username);

}
