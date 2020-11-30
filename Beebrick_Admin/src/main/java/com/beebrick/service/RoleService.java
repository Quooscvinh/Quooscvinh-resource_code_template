package com.beebrick.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.beebrick.entity.Role;

public interface RoleService {
	
	List<Role> findAllRole();
	
	void save(Role role);
	
	void delete(Integer roleID);
	
	Optional<Role> findById(Integer roleID);
	
	Page<Role> findPaginated(int pageNo, int pageSize);
}
