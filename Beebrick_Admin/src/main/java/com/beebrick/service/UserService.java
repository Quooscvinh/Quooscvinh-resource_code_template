package com.beebrick.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.beebrick.entity.User;

public interface UserService {

	void save(User user);

	void delete(Integer userID);

	Optional<User> findById(Integer userID);
	
	Page<User> findPaginated(int pageNo, int pageSize);
	
	Page<User> findPaginated1(String username, int pageNo, int pageSize);
}
