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
		return "redirect:/public/"; // construiremos esta ruta más tarde en otro controlador, la cual será el listado de todos los productos
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
