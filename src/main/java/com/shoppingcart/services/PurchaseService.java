package com.shoppingcart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.models.Product;
import com.shoppingcart.models.Purchase;
import com.shoppingcart.models.User;
import com.shoppingcart.repositories.PurchaseRepository;

@Service
public class PurchaseService {

	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Autowired
	ProductService productService;
	
	public Purchase insertPurchase(Purchase purchase, User user) {
		purchase.setOwner(user);
		return purchaseRepository.save(purchase);
	}
	
	public Purchase insertPurchase(Purchase purchase) {
		return purchaseRepository.save(purchase);
	}
	
	public Product addProductToPurchase(Product product, Purchase purchase) {
		product.setPurchase(purchase);
		return productService.editProduct(product);
	}
	
	public Purchase searchPurchaseById(long id) {
		return purchaseRepository.findById(id).orElse(null);
	}
	
	public List<Purchase> searchAllPurchases() {
		return purchaseRepository.findAll();
	}
	
	public List<Purchase> searchPurchaseByOwner(User user) {
		return purchaseRepository.findByPurchaseOwner(user);
	}
}
