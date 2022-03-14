package com.clienteservidor.spingboot.web.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.clienteservidor.spingboot.web.app.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
