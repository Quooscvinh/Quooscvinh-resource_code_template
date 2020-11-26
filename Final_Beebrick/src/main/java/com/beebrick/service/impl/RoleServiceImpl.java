package com.beebrick.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beebrick.entity.Role;
import com.beebrick.repository.RoleRepository;
import com.beebrick.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> getAllRole() {
		return roleRepository.findAll();
	}

	
}
