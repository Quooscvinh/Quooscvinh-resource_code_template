package com.beebrick.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.beebrick.entity.User;
import com.beebrick.repository.UserRepository;
import com.beebrick.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public void delete(Integer userID) {
		userRepository.delete(userID);
	}

	@Override
	public Optional<User> findById(Integer userID) {
		return userRepository.findById(userID);
	}

	@Override
	public Page<User> findPaginated(int pageNo, int pageSize) {
		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
		return userRepository.getAllUser(pageable);
	}

	@Override
	public Page<User> findPaginated1(String username, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
