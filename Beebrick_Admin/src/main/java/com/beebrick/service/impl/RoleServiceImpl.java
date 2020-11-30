package com.beebrick.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.beebrick.entity.Role;
import com.beebrick.repository.RoleRepository;
import com.beebrick.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> findAllRole() {
		return roleRepository.findAllRole();
	}

	@Override
	public void save(Role role) {
		roleRepository.save(role);
	}

	@Override
	public void delete(Integer roleID) {
		roleRepository.delete(roleID);
	}

	@Override
	public Optional<Role> findById(Integer roleID) {
		return roleRepository.findById(roleID);
	}

	@Override
	public Page<Role> findPaginated(int pageNo, int pageSize) {
		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
		return roleRepository.getAllRole(pageable);
	}
}
