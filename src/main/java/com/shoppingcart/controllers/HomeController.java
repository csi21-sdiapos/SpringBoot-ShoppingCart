package com.shoppingcart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingcart.models.Product;
import com.shoppingcart.services.ProductService;

@Controller
@RequestMapping("/home") // ponemos aquí el prefijo de ruta para este controlador, y así ya no tenemos que volver a repetirlo en las demás rutas que definamos aquí
public class HomeController {

	@Autowired
	ProductService productService;
	
	
	@ModelAttribute // para que una vez que se cree el controlador y lo invoquemos, tengamos siempre el listado de productos sin vender disponible
	public List<Product> productsNotSold() {
		return productService.productsNotSold();
	}
	
	@GetMapping({"/", "/index"})
	public String index(Model model, @RequestParam(name = "q", required = false) String query) {
		if (query != null)
			model.addAttribute("products", productService.searchProducts(query));
		// si por otro caso, la query es nula, estará tirando del listado de productos sin vender
		return "index";
	}
	
	@GetMapping("/product/{id}")
	public String goToProduct(Model model, @PathVariable Long id) {
		Product product = productService.findById(id);
		
		if (product != null)
			model.addAttribute("product", product);
		// si por otro caso, el producto es nulo, se redirige hacia la página del home
		return "redirect:/home";
	}
}
