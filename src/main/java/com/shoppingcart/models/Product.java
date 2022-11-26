package com.shoppingcart.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity
@Table(name = "Products")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	/******************************************* ATRIBUTOS *********************************************/
	@Id
	@Column(name = "ProductId", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
	
	@Column(name = "ProductName", length = 100, nullable = false)
	private String productName;
	
	@Column(name = "ProductPrice", nullable = true)
	private double productPrice;
	
	@Column(name = "ProductImage", nullable = true)
	private String productImage;
	
	// relational field with User (one or more product can be sold by one user)
	@Valid
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private User productOwner;
	
	// relational field with Purchase (one or more product can be included in one purchase)
	@Valid
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "purchaseId")
	private Purchase purchase;

	
	/******************************************* CONSTRUCTORES *********************************************/
	public Product(String productName, double productPrice, String productImage, @Valid User productOwner) { // without id and purchase
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.productImage = productImage;
		this.productOwner = productOwner;
	}

	public Product() {
		super();
	}

	
	/******************************************* GETTERS Y SETTERS *********************************************/
	public long getProductId() {
		return productId;
	}

	public void setId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public User getProductOwner() {
		return productOwner;
	}

	public void setProductOwner(User productOwner) {
		this.productOwner = productOwner;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	/******************************************* MÉTODOS *********************************************/

	
	/******************************************* HashCode y Equals *********************************************/
	@Override
	public int hashCode() {
		return Objects.hash(productId, productImage, productName, productOwner, productPrice, purchase);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Product)) {
			return false;
		}
		Product other = (Product) obj;
		return productId == other.productId && Objects.equals(productImage, other.productImage)
				&& Objects.equals(productName, other.productName) && Objects.equals(productOwner, other.productOwner)
				&& Double.doubleToLongBits(productPrice) == Double.doubleToLongBits(other.productPrice)
				&& Objects.equals(purchase, other.purchase);
	}

	
	/******************************************* ToString *********************************************/
	@Override
	public String toString() {
		return "\nProduct [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productImage=" + productImage + ", productOwner=" + productOwner + ", purchase=" + purchase + "]";
	}
}
