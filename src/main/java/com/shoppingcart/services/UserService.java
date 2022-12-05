package com.shoppingcart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shoppingcart.models.User;
import com.shoppingcart.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User registerUser(User user) {
		user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
		return userRepository.save(user);
	}
	
	public User findById(long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User findByEmail(String email) {
		return userRepository.findFirstByEmail(email);
	}
}
