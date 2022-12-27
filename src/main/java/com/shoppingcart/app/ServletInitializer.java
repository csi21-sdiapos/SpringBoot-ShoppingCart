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
