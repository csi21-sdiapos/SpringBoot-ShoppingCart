package com.shoppingcart.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
// import java.util.Date;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;

@Entity
@Table(name = "Users")
@EntityListeners(AuditingEntityListener.class) // for @CreatedDate (audit)
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/******************************************* ATRIBUTOS *********************************************/
	@Id
	@Column(name = "UserId", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	@Column(name = "UserName", length = 100, nullable = false)
	private String userName;
	
	@Column(name = "UserSurname", length = 100, nullable = false)
	private String userSurname;
	
	@Column(name = "UserAvatar", length = 100, nullable = true)
	private String userAvatar;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UserEntryDate", nullable = false)
	private Date userEntryDate;
	
	@Column(name = "UserEmail", length = 100, nullable = false)
	private String userEmail;
	
	@Column(name = "UserPassword", length = 100, nullable = false)
	private String userPassword;
	
	
	/******************************************* RELACIONES *********************************************/
	// relational field with Purchase (1:N) (one user can make one or more purchases)
	@Valid
	@OneToMany(mappedBy = "purchaseOwner" , fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	Set<Purchase> purchasesList = new HashSet<>();
	
	// relational field with Product (1:N) (one user can sell one or more products)
	@Valid
	@OneToMany(mappedBy = "productOwner" , fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	Set<Product> productsList = new HashSet<>();
	
	
	/******************************************* CONSTRUCTORES *********************************************/
	public User(String userName, String userSurname, String userAvatar, String userEmail, String userPassword) { // without id
		super();
		this.userName = userName;
		this.userSurname = userSurname;
		this.userAvatar = userAvatar;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}

	public User() {
		super();
	}

	
	/******************************************* GETTERS Y SETTERS *********************************************/
	public long getUserId() {
		return userId;
	}

	public void setId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public Date getUserEntryDate() {
		return userEntryDate;
	}

	public void setUserEntryDate(Date userEntryDate) {
		this.userEntryDate = userEntryDate;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	/******************************************* MÉTODOS *********************************************/
	public void addPurchase(Purchase purchase) { // the point of view for the relation between User and Purchase will be, how many purchases has done one user?
		this.purchasesList.add(purchase);
	}
	
	public void addProduct(Product product) { 	 // the point of view for the relation between User and Product will be, how many products has sold one user?
		this.productsList.add(product);
	}
	
	/******************************************* HasCode y Equals *********************************************/
	@Override
	public int hashCode() {
		return Objects.hash(userId, userAvatar, userEmail, userEntryDate, userName, userPassword, userSurname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		return userId == other.userId && Objects.equals(userAvatar, other.userAvatar)
				&& Objects.equals(userEmail, other.userEmail) && Objects.equals(userEntryDate, other.userEntryDate)
				&& Objects.equals(userName, other.userName) && Objects.equals(userPassword, other.userPassword)
				&& Objects.equals(userSurname, other.userSurname);
	}
	
	
	/******************************************* ToString *********************************************/
	@Override
	public String toString() {
		return "\nUser [userId=" + userId + ", userName=" + userName + ", userSurname=" + userSurname + ", userAvatar=" + userAvatar + ", userEntryDate=" + userEntryDate + ", userEmail=" + userEmail + ", userPassword=" + userPassword + "]";
	}
}
