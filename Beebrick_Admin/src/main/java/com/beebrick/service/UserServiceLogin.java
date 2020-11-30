package com.beebrick.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.beebrick.entity.UserLogin;
import com.beebrick.web.dto.UserRegistrationDto;

public interface UserServiceLogin extends UserDetailsService{
	UserLogin save(UserRegistrationDto registrationDto);
}
