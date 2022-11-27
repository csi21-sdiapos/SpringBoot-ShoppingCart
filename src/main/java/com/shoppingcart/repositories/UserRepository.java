package com.shoppingcart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoppingcart.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Devuelve el primer usuario que encuentre a través de un email (el email de un usuario debería de ser único)
	 * @param email
	 * @return
	 */
	User findFirstByEmail(String email);
}
