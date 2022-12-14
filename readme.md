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
- [2. Creación de los repositorios](#2-creación-de-los-repositorios)
	- [2.1. *com.shoppingcart.repositories --\> UserRepository.java*](#21-comshoppingcartrepositories----userrepositoryjava)
	- [2.2. *com.shoppingcart.repositories --\> PurchaseRepository.java*](#22-comshoppingcartrepositories----purchaserepositoryjava)
	- [2.3. *com.shoppingcart.repositories --\> ProductRepository.java*](#23-comshoppingcartrepositories----productrepositoryjava)
	- [2.4. *src/main/resources --\> application.properties*](#24-srcmainresources----applicationproperties)
	- [2.5. *com.shoppingcart.ShoppingCart --\> ShoppingCartApplication.java* (Main)](#25-comshoppingcartshoppingcart----shoppingcartapplicationjava-main)
	- [2.7. Observaciones](#27-observaciones)
		- [Otras formas de generar la base de datos e insertar datos de ejemplo](#otras-formas-de-generar-la-base-de-datos-e-insertar-datos-de-ejemplo)
- [3. Implementación de la seguridad](#3-implementación-de-la-seguridad)
	- [3.1. *com.shoppingcart.implementations --\> UserDetailsServiceImpl.java*](#31-comshoppingcartimplementations----userdetailsserviceimpljava)
	- [3.2. *com.shoppingcart.config --\> SecurityConfig.java*](#32-comshoppingcartconfig----securityconfigjava)
- [4. Creación de los servicios](#4-creación-de-los-servicios)
	- [4.1. *com.shoppingcart.services --\> ProductService.java*](#41-comshoppingcartservices----productservicejava)
	- [4.2. *com.shoppingcart.services --\> UserService.java*](#42-comshoppingcartservices----userservicejava)
	- [4.3. *com.shoppingcart.services --\> PurchaseService.java*](#43-comshoppingcartservices----purchaseservicejava)
	- [4.4. *com.shoppingcart.app --\> ShoppingCartApplication.java* (Main)](#44-comshoppingcartapp----shoppingcartapplicationjava-main)
	- [4.5. *com.shoppingcart.app --\> ServletInitializer.java*](#45-comshoppingcartapp----servletinitializerjava)
- [5. Plantillas de las vistas](#5-plantillas-de-las-vistas)
	- [5.1. *src/main/resources/static/css/styles.css*](#51-srcmainresourcesstaticcssstylescss)
	- [5.2. *src/main/resources/templates/index.html*](#52-srcmainresourcestemplatesindexhtml)
	- [5.3. *src/main/resources/templates/login.html*](#53-srcmainresourcestemplatesloginhtml)
	- [5.4. *src/main/resources/templates/product.html*](#54-srcmainresourcestemplatesproducthtml)
	- [5.5. *src/main/resources/templates/app/product/productsList.html*](#55-srcmainresourcestemplatesappproductproductslisthtml)
	- [5.6. *src/main/resources/templates/app/product/productForm.html*](#56-srcmainresourcestemplatesappproductproductformhtml)
	- [5.7. *src/main/resources/templates/app/purchase/purchasesList.html*](#57-srcmainresourcestemplatesapppurchasepurchaseslisthtml)
	- [5.8. *src/main/resources/templates/app/purchase/shoppingCart.html*](#58-srcmainresourcestemplatesapppurchaseshoppingcarthtml)
	- [5.9. *src/main/resources/templates/app/purchase/invoice.html*](#59-srcmainresourcestemplatesapppurchaseinvoicehtml)
- [6. Login y Register](#6-login-y-register)
	- [*com.shoppingcart.controllers --\> LoginController.java*](#comshoppingcartcontrollers----logincontrollerjava)
- [7. Listado de productos](#7-listado-de-productos)
- [Errores](#errores)

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
		<version>3.0.0</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	
	<groupId>com.shoppingcart</groupId>
	<artifactId>ShoppingCart</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>war</packaging>
	<name>ShoppingCart</name>
	<description>Spring MVC project</description>
	
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<java.version>1.8</java.version>
		<maven.compiler.source>1.8</maven.compiler.source>
    	<maven.compiler.target>1.8</maven.compiler.target>
    	<start-class>com.shoppingcart.app.ShoppingCartApplication</start-class>
    	<!-- <org.springframework-version>5.3.23</org.springframework-version> -->
    	<!-- <hibernate.version>5.6.12.Final</hibernate.version> -->
		<!-- <postgresql.connector.version>42.5.0</postgresql.connector.version> -->
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
		
		<dependency>
		    <groupId>org.springframework.security</groupId>
		    <artifactId>spring-security-web</artifactId>
		    <!-- <version>6.0.0</version> -->
		</dependency>
		
		<!-- _______________________________________ Database _________________________________________ -->
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>

		<dependency>
		    <groupId>org.postgresql</groupId>
		    <artifactId>postgresql</artifactId>
		    <!-- <version>42.5.0</version> -->
		</dependency>
	
		<!-- _______________________________________ Thymeleaf _________________________________________ -->
		<dependency>
			<groupId>org.thymeleaf.extras</groupId>
			<artifactId>thymeleaf-extras-springsecurity6</artifactId>
		</dependency>
<!--
		<dependency>
		    <groupId>org.thymeleaf</groupId>
		    <artifactId>thymeleaf</artifactId>
		    <version>3.0.15.RELEASE</version>
		</dependency>
-->
	
		<!-- _______________________________________ Jakarta _________________________________________ -->
		<dependency>
		    <groupId>jakarta.servlet</groupId>
		    <artifactId>jakarta.servlet-api</artifactId>
		    <!-- <version>6.0.0</version> -->
		    <scope>provided</scope>
		</dependency>
<!--
		<dependency>
		    <groupId>jakarta.servlet.jsp</groupId>
		    <artifactId>jakarta.servlet.jsp-api</artifactId>
		    <version>3.1.0</version>
		    <scope>provided</scope>
		</dependency>
-->
<!--
		<dependency>
		    <groupId>jakarta.servlet.jsp.jstl</groupId>
		    <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
		    <version>3.0.0</version>
		</dependency>
-->
<!--
		<dependency>
		    <groupId>jakarta.annotation</groupId>
		    <artifactId>jakarta.annotation-api</artifactId>
		    <version>2.1.1</version>
		</dependency>
-->
<!--
		<dependency>
		    <groupId>jakarta.xml.bind</groupId>
		    <artifactId>jakarta.xml.bind-api</artifactId>
		    <version>4.0.0</version>
		</dependency>
-->
<!--
		<dependency>
		    <groupId>jakarta.inject</groupId>
		    <artifactId>jakarta.inject-api</artifactId>
		    <version>2.0.1</version>
		</dependency>
-->
<!--
		<dependency>
		    <groupId>jakarta.ws.rs</groupId>
		    <artifactId>jakarta.ws.rs-api</artifactId>
		    <version>3.1.0</version>
		</dependency>
-->
<!--
		<dependency>
		    <groupId>jakarta.validation</groupId>
		    <artifactId>jakarta.validation-api</artifactId>
		    <version>3.0.2</version>
		</dependency>
-->
		<dependency>
		    <groupId>jakarta.persistence</groupId>
		    <artifactId>jakarta.persistence-api</artifactId>
		    <!-- <version>3.1.0</version> -->
		</dependency>
<!--
		<dependency>
		    <groupId>jakarta.activation</groupId>
		    <artifactId>jakarta.activation-api</artifactId>
		    <version>2.1.0</version>
		</dependency>
-->
	
		<!-- _______________________________________ Javax _________________________________________ -->
		<dependency>
		    <groupId>javax.servlet.jsp</groupId>
		    <artifactId>javax.servlet.jsp-api</artifactId>
		    <version>2.3.3</version>
		    <scope>provided</scope>
		</dependency>
		
		<dependency>
		    <groupId>javax.servlet</groupId>
		    <artifactId>jstl</artifactId>
		    <version>1.2</version>
		</dependency>
		
		<dependency>
		    <groupId>javax.persistence</groupId>
		    <artifactId>javax.persistence-api</artifactId>
		    <version>2.2</version>
		</dependency>
		
		<!-- _______________________________________ Hibernate _________________________________________ -->
		<dependency>
		    <groupId>org.hibernate.validator</groupId>
		    <artifactId>hibernate-validator</artifactId>
		    <!-- <version>8.0.0.Final</version> -->
		</dependency>
	
		<dependency>
		    <groupId>org.hibernate.orm</groupId>
		    <artifactId>hibernate-core</artifactId>
		    <!-- <version>6.1.5.Final</version> -->
		</dependency>
		
		<dependency>
		    <groupId>org.hibernate</groupId>
		    <artifactId>hibernate-core-jakarta</artifactId>
		    <version>5.6.14.Final</version>
		</dependency>
<!--
		<dependency>
		    <groupId>org.hibernate</groupId>
		    <artifactId>hibernate-annotations</artifactId>
		    <version>3.5.6-Final</version>
		</dependency>
-->
<!--
		<dependency>
		    <groupId>org.hibernate.javax.persistence</groupId>
		    <artifactId>hibernate-jpa-2.1-api</artifactId>
		    <version>1.0.2.Final</version>
		</dependency>
-->
<!--
		<dependency>
		    <groupId>org.eclipse.persistence</groupId>
		    <artifactId>org.eclipse.persistence.jpa</artifactId>
		    <version>4.0.0</version>
		</dependency>
-->

		<!-- _______________________________________ Tiles _________________________________________ -->
<!--
		<dependency>
		    <groupId>org.apache.tiles</groupId>
		    <artifactId>tiles-jsp</artifactId>
		    <version>3.0.8</version>
		</dependency>
-->
<!--	
		<dependency>
		    <groupId>org.apache.tiles</groupId>
		    <artifactId>tiles-core</artifactId>
		    <version>3.0.8</version>
		</dependency>
-->
<!--
		<dependency>
		    <groupId>org.apache.tiles</groupId>
		    <artifactId>tiles-api</artifactId>
		    <version>3.0.8</version>
		</dependency>
-->
<!--
		<dependency>
		    <groupId>org.apache.tiles</groupId>
		    <artifactId>tiles-servlet</artifactId>
		    <version>3.0.8</version>
		</dependency>
-->
<!--
		<dependency>
		    <groupId>org.apache.tiles</groupId>
		    <artifactId>tiles-template</artifactId>
		    <version>3.0.8</version>
		</dependency>
-->

		<!-- _______________________________________ MapStruct _________________________________________ -->
<!--
		<dependency>
		    <groupId>org.mapstruct</groupId>
		    <artifactId>mapstruct</artifactId>
		    <version>1.5.3.Final</version>
		</dependency>
-->
<!--	
		<dependency>
		    <groupId>org.mapstruct</groupId>
		    <artifactId>mapstruct-processor</artifactId>
		    <version>1.5.3.Final</version>
		</dependency>
-->
	
		<!-- _______________________________________ Log4j2 _________________________________________ -->
		<!-- log4j2 – Failed to load class “org.slf4j.impl.StaticLoggerBinder” -->
		<!-- https://mkyong.com/java/log4j2-failed-to-load-class-org-slf4j-impl-staticloggerbinder/ -->
<!--
		<dependency>
		    <groupId>org.apache.logging.log4j</groupId>
		    <artifactId>log4j-core</artifactId>
		    <version>2.19.0</version>
		</dependency>
-->
<!--
		<dependency>
		    <groupId>org.apache.logging.log4j</groupId>
		    <artifactId>log4j-slf4j-impl</artifactId>
		    <version>2.19.0</version>
		    <scope>test</scope>
		</dependency>
-->

		<!-- _______________________________________ TagLibs _________________________________________ -->
		<dependency>
		    <groupId>org.apache.taglibs</groupId>
		    <artifactId>taglibs-standard-impl</artifactId>
		    <version>1.2.5</version>
		    <scope>runtime</scope>
		</dependency>
	
	</dependencies>

	<build>
		<plugins>

			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
			
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-resources-plugin</artifactId>
				<!-- <version>3.1.0</version> -->
				<!-- Add the following exclusions here. -->
				<configuration>
					<nonFilteredFileExtensions>
						<nonFilteredFileExtension>properties</nonFilteredFileExtension>
					</nonFilteredFileExtensions>
				</configuration>
			</plugin>

<!--	
			<plugin>
		    	<artifactId>maven-clean-plugin</artifactId>
		        <version>3.2.0</version>
		 	</plugin>
-->
<!--	      
	        <plugin>
		        <groupId>org.apache.maven.plugins</groupId>
		        <artifactId>maven-war-plugin</artifactId>
		        <version>3.3.2</version>
		        <configuration>
					<failOnMissingWebXml>false</failOnMissingWebXml>
				</configuration>
		    </plugin>
-->
<!--        
	        <plugin>
		        <groupId>org.apache.maven.plugins</groupId>
		        <artifactId>maven-compiler-plugin</artifactId>
		        <version>3.10.1</version>
		        <configuration>
		        	<source>1.8</source>
		          	<target>1.8</target>
		          
		          	<annotationProcessorPaths>
	                	<path>
	                    	<groupId>org.mapstruct</groupId>
						    <artifactId>mapstruct-processor</artifactId>
						    <version>1.5.3.Final</version>
	                	</path>
	            	</annotationProcessorPaths>
		        </configuration>
			</plugin>
-->
<!--	      
		    <plugin>
		    	<groupId>org.apache.maven.plugins</groupId>
		        <artifactId>maven-surefire-plugin</artifactId>
		        <version>3.0.0-M7</version>
		    </plugin>
-->
			<plugin>  
			    <groupId>org.apache.maven.plugins</groupId>  
			    <artifactId>maven-surefire-plugin</artifactId>  
			    <!-- <version>2.22.2</version> --> 
			    <configuration>  
			    <skipTests>true</skipTests>  
			    </configuration>  
			</plugin>  
<!--	      
		    <plugin>
	        	<artifactId>maven-install-plugin</artifactId>
	          	<version>3.0.1</version>
	        </plugin>
-->
<!--        
	        <plugin>
	          <artifactId>maven-deploy-plugin</artifactId>
	          <version>3.0.0</version>
	        </plugin>
-->
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
	//	@Temporal(TemporalType.TIMESTAMP)
	@Temporal(TemporalType.DATE) // https://stackoverflow.com/questions/25333711/what-is-the-use-of-the-temporal-annotation-in-hibernate
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
	//	@Temporal(TemporalType.TIMESTAMP)
	@Temporal(TemporalType.DATE) // https://stackoverflow.com/questions/25333711/what-is-the-use-of-the-temporal-annotation-in-hibernate
	@Column(name = "PurchaseDate", nullable = false)
	private Date purchaseEntryDate;
	

	/******************************************* RELACIONES *********************************************/
	// relational field with User (N:1) (one or more purchases can be made by one user)
	@Valid
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private User purchaseOwner;

	// relational field with Product (1:N) (one purchase can include one or more products)
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
	

	/******************************************* RELACIONES *********************************************/
	// relational field with User (N:1) (one or more product can be sold by one user)
	@Valid
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private User productOwner;
	
	// relational field with Purchase (N:1) (one or more product can be included in one purchase)
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

# 2. Creación de los repositorios

## 2.1. *com.shoppingcart.repositories --> UserRepository.java*

```java
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
```

## 2.2. *com.shoppingcart.repositories --> PurchaseRepository.java*

```java
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
```

## 2.3. *com.shoppingcart.repositories --> ProductRepository.java*

```java
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
```

## 2.4. *src/main/resources --> application.properties*

```properties
# La propiedad spring.jpa.hibernate.ddl-auto (la cual es un atajo de la propiedad de hibernate hibernate.hbm2ddl.auto) 
# toma el valor create-drop cuando se utiliza una base de datos embebida (por ejemplo H2) y no se le indica otra forma de trabajar. 
# En otro caso, tomar el valor none. Si quisiéramos gestionar nosotros la creación de la base de datos, ese sería el valor que tendríamos que indicar
spring.jpa.hibernate.ddl-auto=create-drop
```

## 2.5. *com.shoppingcart.ShoppingCart --> ShoppingCartApplication.java* (Main)

Para cargar una serie de datos de forma rápida en los comienzos de nuestro desarrollo, podemos utilizar la clase *CommandLineRunner*

```java
package com.shoppingcart.ShoppingCart;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.shoppingcart.models.Product;
import com.shoppingcart.models.User;
import com.shoppingcart.repositories.ProductRepository;
import com.shoppingcart.repositories.UserRepository;

@SpringBootApplication
public class ShoppingCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initData(UserRepository userRepository, ProductRepository productRepository) {
		return args -> {
			
			User user1 = new User("sergio", "diaz campos", null, "sergio@gmail.com", "123");
			user1 = userRepository.save(user1);
			
			productRepository.saveAll(Arrays.asList(
					new Product("Bicicleta de montaña", 100.0f, "https://www.decathlon.es/media/835/8350582/big_23c25284-2810-415d-8bcc-e6bebdb536fc.jpg", user1),
					new Product("Golf GTI Serie 2", 2500.0f, "https://www.minicar.es/large/Volkswagen-Golf-GTi-G60-Serie-II-%281990%29-Norev-1%3A18-i22889.jpg", user1),
					new Product("Raqueta de tenis", 10.5f, "https://imgredirect.milanuncios.com/fg/2311/04/tenis/Raqueta-tenis-de-segunda-mano-en-Madrid-231104755_1.jpg?VersionId=T9dPhTf.3ZWiAFjnB7CvGKsvbdfPLHht", user1),
					new Product("Xbox One X", 425.0f, "https://images.vibbo.com/635x476/860/86038583196.jpg", user1),
					new Product("Tripode flexible", 10.0f, "https://images.vibbo.com/635x476/860/86074256163.jpg", user1),
					new Product("Iphone 7 128 GB", 350.0f, "https://store.storeimages.cdn-apple.com/4667/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ph/iphone7/rosegold/iphone7-rosegold-select-2016?wid=470&hei=556&fmt=jpeg&qlt=95&op_usm=0.5,0.5&.v=1472430205982", user1)
			));
		};
	}
}
```

## 2.7. Observaciones

### Otras formas de generar la base de datos e insertar datos de ejemplo

Como muchos podréis suponer, la forma en que hemos ido generando la base de datos e insertando datos de ejemplo no es la más ortodoxa, aunque es muy útil mientras aprendemos a desarrollar. Sin embargo, con un sistema en producción, dicho esquema de trabajo no sería el más viable.

En el caso de usar un SGBD más potente y remoto, lo normal es generar el esquema de la base de datos utilizando scripts de sql. Estos scripts suelen ser acumulativos; es decir, para la versión 3, lo que hará el script es modificar lo necesario de la versión 2, y añadir todos los elementos nuevos. Además, también se suelen usar scripts de sql para hacer la inserción de los datos iniciales.

Spring Boot, como en otras ocasiones, nos ofrece facilidades para la creación del esquema y la inserción de datos iniciales.

- La propiedad spring.jpa.hibernate.ddl-auto (la cual es un atajo de la propiedad de hibernate hibernate.hbm2ddl.auto) toma el valor create-drop cuando se utiliza una base de datos embebida (por ejemplo H2) y no se le indica otra forma de trabajar. En otro caso, tomar el valor none. Si quisiéramos gestionar nosotros la creación de la base de datos, ese sería el valor que tendríamos que indicar

> spring.jpa.hibernate.ddl-auto=none

- El script de creación de la base de datos debe llamarse schema.sql y debe colocarse en algún lugar del classpath. Un buen sitio para ello podría ser la ruta src/main/resources/, donde está el propio fichero application.properties.
- El script de inicialización de datos debe llamarse data.sql y debe colocarse en el mismo lugar del fichero anterior.

# 3. Implementación de la seguridad

*AuthenticationManagerBuilder* nos permite (casi) cualquier manera de autentificarnos, siendo una de ellas a través de un servicio de ***UserDetailsService***, el cual nos permite que seamos nosotros los que implementemos un servicio que se encargará de gestionar algunas tareas como de la búsqueda del usuario para la autentificación... entonces seremos nosotros quienes vamos a definir, que busque dentro de nuestro repositorio de usuarios.

![](./img/7.png)

![](./img/8.png)

## 3.1. *com.shoppingcart.implementations --> UserDetailsServiceImpl.java*

```java
package com.shoppingcart.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
```

## 3.2. *com.shoppingcart.config --> SecurityConfig.java*

```java
package com.shoppingcart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	UserDetailsService userDetailsService;
	    
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {   
		return new BCryptPasswordEncoder();
	}
		
	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return
			http
	        	.getSharedObject(AuthenticationManagerBuilder.class)
	        		.userDetailsService(userDetailsService)
	        		.passwordEncoder(passwordEncoder())
	        		.and()
	        		
	        	.build();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests()
				.requestMatchers("/", "/webjars/**", "/css/**", "/h2-console/**", "/home/**", "/auth/**", "/files/**")
				.permitAll()
				.anyRequest().authenticated()
				.and()
				
			.formLogin()
				.loginPage("/auth/login")
				.defaultSuccessUrl("/home/index", true)
				.loginProcessingUrl("/auth/login-post")
				.permitAll()
				.and()
				
			.logout()
				.logoutUrl("/auth/logout")
				.logoutSuccessUrl("/home/index");
	       
		http.csrf().disable();				   		// to access to h2 db
		http.headers().frameOptions().disable();	// to access to h2 db
			
		return http.build();
	}
}
```

# 4. Creación de los servicios

## 4.1. *com.shoppingcart.services --> ProductService.java*

```java
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
```

## 4.2. *com.shoppingcart.services --> UserService.java*

```java
package com.shoppingcart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shoppingcart.models.User;
import com.shoppingcart.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User registerUser(User user) {
		user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
		return userRepository.save(user);
	}
	
	public User findById(long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User findByEmail(String email) {
		return userRepository.findFirstByEmail(email);
	}
}
```

## 4.3. *com.shoppingcart.services --> PurchaseService.java*

```java
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
```

## 4.4. *com.shoppingcart.app --> ShoppingCartApplication.java* (Main)

```java
package com.shoppingcart.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shoppingcart.models.Product;
import com.shoppingcart.models.User;
import com.shoppingcart.services.ProductService;
import com.shoppingcart.services.UserService;

@SpringBootApplication
public class ShoppingCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-jpa-hibernate-context.xml");
		UserService userService = applicationContext.getBean(UserService.class);
		ProductService productService = applicationContext.getBean(ProductService.class);
	}
	
	@Bean
	public CommandLineRunner initData(UserService userService, ProductService productService) {
		return args -> {
		
			User user1 = new User("Luis Miguel", "López Magaña", null, "luismi.lopez@openwebinars.net", "luismi");
			user1 = userService.registerUser(user1);

			User user2 = new User("Antonio", "García Martín", null, "antonio.garcia@openwebinars.net", "antonio");
			user2 = userService.registerUser(user2);
			
			List<Product> productsList = Arrays.asList
				(
					new Product("Bicicleta de montaña", 100.0f, "https://www.decathlon.es/media/835/8350582/big_23c25284-2810-415d-8bcc-e6bebdb536fc.jpg", user1),
					new Product("Golf GTI Serie 2", 2500.0f, "https://www.minicar.es/large/Volkswagen-Golf-GTi-G60-Serie-II-%281990%29-Norev-1%3A18-i22889.jpg", user1),
					new Product("Raqueta de tenis", 10.5f, "https://imgredirect.milanuncios.com/fg/2311/04/tenis/Raqueta-tenis-de-segunda-mano-en-Madrid-231104755_1.jpg?VersionId=T9dPhTf.3ZWiAFjnB7CvGKsvbdfPLHht", user1),
					new Product("Xbox One X", 425.0f, "https://images.vibbo.com/635x476/860/86038583196.jpg", user2),
					new Product("Trípode flexible", 10.0f, "https://images.vibbo.com/635x476/860/86074256163.jpg", user2),
					new Product("Iphone 7 128 GB", 350.0f, "https://store.storeimages.cdn-apple.com/4667/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ph/iphone7/rosegold/iphone7-rosegold-select-2016?wid=470&hei=556&fmt=jpeg&qlt=95&op_usm=0.5,0.5&.v=1472430205982", user2));
			
			productsList.forEach(productService::insertProduct);
		};
	}	
}
```

## 4.5. *com.shoppingcart.app --> ServletInitializer.java*

```java
package com.shoppingcart.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// Consider defining a bean of type 'package' in your configuration
// https://stackoverflow.com/questions/40384056/consider-defining-a-bean-of-type-package-in-your-configuration-spring-boot
@SpringBootApplication
@ComponentScan({
	"com.shoppingcart.app",
	"com.shoppingcart.config",
	"com.shoppingcart.controllers",
	"com.shoppingcart.implementations",
	"com.shoppingcart.models",
	"com.shoppingcart.repositories",
	"com.shoppingcart.services"
})
@EntityScan({
	"com.shoppingcart.app",
	"com.shoppingcart.config",
	"com.shoppingcart.controllers",
	"com.shoppingcart.implementations",
	"com.shoppingcart.models",
	"com.shoppingcart.repositories",
	"com.shoppingcart.services"
})
@EnableJpaRepositories({
	"com.shoppingcart.app",
	"com.shoppingcart.config",
	"com.shoppingcart.controllers",
	"com.shoppingcart.implementations",
	"com.shoppingcart.models",
	"com.shoppingcart.repositories",
	"com.shoppingcart.services"
})
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ShoppingCartApplication.class);
	}
}
```

# 5. Plantillas de las vistas

## 5.1. *src/main/resources/static/css/styles.css*

[styles.css](./src/main/resources/static/css/styles.css)

## 5.2. *src/main/resources/templates/index.html*

[index.html](./src/main/resources/templates/index.html)

## 5.3. *src/main/resources/templates/login.html*

[login.html](./src/main/resources/templates/login.html)

## 5.4. *src/main/resources/templates/product.html*

[product.html](./src/main/resources/templates/Product.html)

## 5.5. *src/main/resources/templates/app/product/productsList.html*

[productsList.html](./src/main/resources/templates/app/product/productsList.html)

## 5.6. *src/main/resources/templates/app/product/productForm.html*

[productForm.html](./src/main/resources/templates/app/product/productForm.html)

## 5.7. *src/main/resources/templates/app/purchase/purchasesList.html*

[purchasesList.html](./src/main/resources/templates/app/purchase/purchasesList.html)

## 5.8. *src/main/resources/templates/app/purchase/shoppingCart.html*

[shoppingCart.html](./src/main/resources/templates/app/purchase/shoppingCart.html)

## 5.9. *src/main/resources/templates/app/purchase/invoice.html*

[invoice.html](./src/main/resources/templates/app/purchase/invoice.html)

# 6. Login y Register

## *com.shoppingcart.controllers --> LoginController.java*

```java
package com.shoppingcart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.shoppingcart.models.User;
import com.shoppingcart.services.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String welcome() {
		return "redirect:/home/"; // construiremos esta ruta más tarde en otro controlador, la cual será el listado de todos los productos
	}
	
	@GetMapping("/auth/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	// aquí no necesitaríamos hacer el PostMapping del Login porque ya lo tenemos dentro del circuito de Spring Security
	@PostMapping("/auth/register")
	public String register(@ModelAttribute User user) { // inyectamos aquí el usuario que habíamos pasado antes
		userService.registerUser(user);
		return "redirect:/auth/login"; // una vez que el usuario se registra, para que en lugar de mandar al usuario directamente al login, lo mandásemos a la página principal (home), tendríamos que saber más de Spring Security, y exponer el servicio de autentificación...
	}
}
```

# 7. Listado de productos

# Errores

1. org.springframework.beans.factory.UnsatisfiedDependencyException:
- Error creating bean with name 'securityConfig': Unsatisfied dependency expressed through field 'userDetailsService'

2. *caused by:* org.springframework.beans.factory.UnsatisfiedDependencyException:
- Error creating bean with name 'userDetailsService': Unsatisfied dependency expressed through field 'userRepository'

3. *caused by:* org.springframework.beans.factory.BeanCreationException:
- Error creating bean with name 'userRepository' defined in com.shoppingcart.repositories.UserRepository defined in @EnableJpaRepositories declared on ServletInitializer

4. *caused by:* org.springframework.data.repository.query.QueryCreationException:
- Could not create query for public abstract com.shoppingcart.models.User com.shoppingcart.repositories.UserRepository.findFirstByEmail(java.lang.String)

5. *caused by:* java.lang.IllegalArgumentException:
- Failed to create query for method public abstract com.shoppingcart.models.User com.shoppingcart.repositories.UserRepository.findFirstByEmail(java.lang.String)

6. *caused by:* org.springframework.data.mapping.PropertyReferenceException:
- No property 'email' found for type 'User'