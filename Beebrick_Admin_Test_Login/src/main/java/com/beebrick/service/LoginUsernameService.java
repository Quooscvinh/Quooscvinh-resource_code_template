package com.beebrick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.beebrick.controller.LoginUserDetail;
import com.beebrick.entity.User;
import com.beebrick.repository.UserRepository;

public class LoginUsernameService implements UserDetailsService {
	
	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new LoginUserDetail(user);
	}

}
