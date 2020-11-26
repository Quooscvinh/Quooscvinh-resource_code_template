package com.beebrick.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.beebrick.entity.User;

public interface UserService {

	void saveUser(User user);

	void deleteUser(Integer userID);

	Optional<User> findUserById(Integer userID);
	
	Page<User> findPaginated(int pageNo, int pageSize);
	
	Page<User> findPaginated1(String username, int pageNo, int pageSize);

	List<User> listAll(String keyword);
}
