package com.shoppingcart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.models.Product;
import com.shoppingcart.models.Purchase;
import com.shoppingcart.models.User;
import com.shoppingcart.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public Product insertProduct(Product product) {
		return productRepository.save(product);
	}
	
	public void deleteProduct(long id) {
		productRepository.deleteById(id);
	}
	
	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}
	
	public Product editProduct(Product product) {
		return productRepository.save(product);
	}
	
	public Product findById(long id) {
		return productRepository.findById(id).orElse(null);
	}
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	/**********************************************************************************************/
	
	public List<Product> productsOfOneOwner(User user) {
		return productRepository.findByProductOwner(user);
	}
	
	public List<Product> productsOfOnePurchase(Purchase purchase) {
		return productRepository.findByPurchase(purchase);
	}
	
	public List<Product> productsNotSold() {
		return productRepository.findByPurchaseIsNull();
	}
	
	public List<Product> searchProducts (String query) {
		return productRepository.findByNameContainsIgnoreCaseAndPurchaseIsNull(query);
	}
	
	public List<Product> searchMyProducts(String query, User user) {
		return productRepository.findByNameContainsIgnoreCaseAndProductOwner(query, user);
	}
	
	public List<Product> searchProductsById(List<Long> ids) {
		return productRepository.findAllById(ids);
	}
	
}
