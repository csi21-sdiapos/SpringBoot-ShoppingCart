package com.shoppingcart.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
//import java.util.Date;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;

@Entity
@Table(name = "Purchases")
@EntityListeners(AuditingEntityListener.class) // for @CreatedDate (audit)
public class Purchase implements Serializable {

	private static final long serialVersionUID = 1L;

	/******************************************* ATRIBUTOS *********************************************/
	@Id
	@Column(name = "PurchaseId", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long purchaseId;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PurchaseDate", nullable = false)
	private Date purchaseEntryDate;
	
	// relational field with User (one or more purchases can be made by one user)
	@Valid
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private User purchaseOwner;

	// relational field with Product (one purchase can include one or more products)
	@Valid
	@OneToMany(mappedBy = "purchase" , fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	Set<Product> productsList = new HashSet<>();
	
	
	/******************************************* CONSTRUCTORES *********************************************/
	public Purchase(User purchaseOwner) { // without id and date
		super();
		this.purchaseOwner = purchaseOwner;
	}

	public Purchase() {
		super();
	}

	
	/******************************************* GETTER Y SETTERS *********************************************/
	public long getPurchaseId() {
		return purchaseId;
	}

	public void setId(long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Date getPurchaseEntryDate() {
		return purchaseEntryDate;
	}

	public void setPurchaseEntryDate(Date purchaseEntryDate) {
		this.purchaseEntryDate = purchaseEntryDate;
	}

	public User getPurchaseOwner() {
		return purchaseOwner;
	}

	public void setOwner(User purchaseOwner) {
		this.purchaseOwner = purchaseOwner;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	/******************************************* MÉTODOS *********************************************/
	public void addProduct(Product product) { // the point of view for the relation between Purchase and Product will be, how many products are included in one purchase?
		this.productsList.add(product);
	}
	
	
	/******************************************* HashCode y Equals *********************************************/
	@Override
	public int hashCode() {
		return Objects.hash(purchaseId, purchaseOwner, purchaseEntryDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Purchase)) {
			return false;
		}
		Purchase other = (Purchase) obj;
		return purchaseId == other.purchaseId && Objects.equals(purchaseOwner, other.purchaseOwner)
				&& Objects.equals(purchaseEntryDate, other.purchaseEntryDate);
	}

	
	/******************************************* ToString *********************************************/
	@Override
	public String toString() {
		return "\nPurchase [purchaseId=" + purchaseId + ", purchaseEntryDate=" + purchaseEntryDate + ", purchaseOwner=" + purchaseOwner + "]";
	}
}
