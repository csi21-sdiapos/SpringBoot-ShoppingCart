package com.shoppingcart.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

// import com.shoppingcart.models.User; // use it directly to call our class User which has the same name of the User in UserDetails
import com.shoppingcart.repositories.UserRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		com.shoppingcart.models.User user = userRepository.findFirstByEmail(username);
		
		UserBuilder userBuilder = null;
		
		if (user != null) {
			userBuilder = User.withUsername(username);
			userBuilder.disabled(false);
			userBuilder.password(user.getUserPassword());
			userBuilder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
		}
		else {
			throw new UsernameNotFoundException("No se ha encontrado el usuario: " + username);
		}
		
		return userBuilder.build();
	}
}
