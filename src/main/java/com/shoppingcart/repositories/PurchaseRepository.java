package com.shoppingcart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoppingcart.models.Purchase;
import com.shoppingcart.models.User;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	/**
	 * Devuelve la lista de compras de un usuario en particular
	 * @param purchaseOwner
	 * @return 
	 */
	List<Purchase> findByPurchaseOwner(User purchaseOwner);
}
