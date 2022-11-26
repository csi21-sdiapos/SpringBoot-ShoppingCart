# Proyecto de Spring Framework - SpringBoot - Spring MVC

- [Proyecto de Spring Framework - SpringBoot - Spring MVC](#proyecto-de-spring-framework---springboot---spring-mvc)
- [0. Creación del proyecto](#0-creación-del-proyecto)
- [0.1. *pom.xml*](#01-pomxml)
- [0.2. *src/main/resources --\> application.properties*](#02-srcmainresources----applicationproperties)
- [1. Creación de las entidades](#1-creación-de-las-entidades)
	- [1.1. *com.shoppingcart.models --\> User.java*](#11-comshoppingcartmodels----userjava)
	- [1.2. *com.shoppingcart.models --\> Purchase.java*](#12-comshoppingcartmodels----purchasejava)
	- [1.3. *com.shoppingcart.models --\> Product.java*](#13-comshoppingcartmodels----productjava)
	- [1.4. *com.shoppingcart.config --\> AuditConfig.java*](#14-comshoppingcartconfig----auditconfigjava)

# 0. Creación del proyecto

![](./img/1.png)

![](./img/2.png)

![](./img/3.png)

![](./img/4.png)

![](./img/5.png)

# 0.1. *pom.xml*

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.6</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	
	<groupId>com.example</groupId>
	<artifactId>ShoppingCart</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>war</packaging>
	<name>ShoppingCart</name>
	<description>online-ecommerce</description>
	
	<properties>
		<java.version>19</java.version>
	</properties>
	
	<dependencies>
	
	<!-- _______________________________________ Spring _________________________________________ -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.session</groupId>
			<artifactId>spring-session-data-redis</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-test</artifactId>
			<scope>test</scope>
		</dependency>
		
		<!-- _______________________________________ Database _________________________________________ -->
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		
		<!-- _______________________________________ Thymeleaf _________________________________________ -->
		<dependency>
			<groupId>org.thymeleaf.extras</groupId>
			<artifactId>thymeleaf-extras-springsecurity5</artifactId>
		</dependency>
		
		<!-- _______________________________________ WebJars _________________________________________ -->
		<dependency>
		    <groupId>org.webjars</groupId>
		    <artifactId>bootstrap</artifactId>
		    <version>5.2.2</version>
		</dependency>
		
		<dependency>
		    <groupId>org.webjars</groupId>
		    <artifactId>jquery</artifactId>
		    <version>3.6.1</version>
		</dependency>
		
		<dependency>
		    <groupId>org.webjars</groupId>
		    <artifactId>webjars-locator</artifactId>
		    <version>0.46</version>
		</dependency>

	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```

# 0.2. *src/main/resources --> application.properties*

```properties
# Puerto de la aplicacion
server.port=9000
# Configuracion del almacenamiento de sesiones con Redis
spring.session.store-type=redis

# URL jdbc de conexion a la base de datos
spring.datasource.url=jdbc:h2:./ShoppingCart

# Usuario y contraseña de la base de datos
spring.datasource.username=h2
spring.datasource.password=12345

# Habilitamos la consola de H2
# http://localhost:{server.port}/h2-console
# En nuestro caso http://localhost:9000/h2-console
spring.h2.console.enabled=true

# Habilitamos los mensajes sql en el log
spring.jpa.show-sql=true


spring.thymeleaf.cache=false
```

# 1. Creación de las entidades

## 1.1. *com.shoppingcart.models --> User.java*

```java
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
	
	// relational field with Purchase (one user can make one or more purchases)
	@Valid
	@OneToMany(mappedBy = "purchaseOwner" , fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	Set<Purchase> purchasesList = new HashSet<>();
	
	// relational field with Product (one user can sell one or more products)
	@Valid
	@OneToMany(mappedBy = "productOwner" , fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	Set<Product> productsList = new HashSet<>();
	
	
	/******************************************* CONSTRUCTORES *********************************************/
	public User(String userName, String userSurname, String userAvatar, Date userEntryDate, String userEmail,
			String userPassword) { // without id
		super();
		this.userName = userName;
		this.userSurname = userSurname;
		this.userAvatar = userAvatar;
		this.userEntryDate = userEntryDate;
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
```

## 1.2. *com.shoppingcart.models --> Purchase.java*

```java
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
```

## 1.3. *com.shoppingcart.models --> Product.java*

```java
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
```

## 1.4. *com.shoppingcart.config --> AuditConfig.java*

```java
package com.shoppingcart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditConfig {
	
	// with this class, Date fields will be automatics (system timestamp)
}
```

