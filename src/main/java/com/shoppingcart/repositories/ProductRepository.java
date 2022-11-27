package com.shoppingcart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoppingcart.models.Product;
import com.shoppingcart.models.Purchase;
import com.shoppingcart.models.User;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	/**
	 * para buscar todos los productos de un propietario
	 * @param productOwner
	 * @return lista de productos
	 */
	List<Product> findByProductOwner(User productOwner);
	
	/**
	 * para buscar todos los productos de una compra
	 * @param purchase
	 * @return lista de productos
	 */
	List<Product> findByPurchase(Purchase purchase);
	
	/**
	 * para buscar todos los productos de una compra que sea nula (es decir, los productos que aún estén en venta)
	 * @return lista de productos
	 */
	List<Product> findByPrurchaseIsNull();
	
	/**
	 * para buscar los productos que contengan el nombre que se pasa como parámetro y su compra aún sea nula
	 * @param name
	 * @return lista de productos
	 */
	List<Product> findByNameContainsIgnoreCaseAndPurchaseIsNull(String name);
	
	/**
	 * para buscar los productos donde el propietario y el nombre sean los que le proporcionemos
	 * @param name
	 * @param productOwner
	 * @return lista de productos
	 */
	List<Product> findByNameContainsIgnoreCaseAndProductOwner(String name, User productOwner);
}
