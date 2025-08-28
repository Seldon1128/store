package com.product;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/category")
public class CtrlProduct {
	
	@GetMapping
	public List<Category> getCategories(){
		//Lista de categorias
		List<Category> categories = new ArrayList<Category>();
		categories.add(new Category(1,"Lentes","Lts",1));
		categories.add(new Category(2,"Relojes","Rljs",1));
		categories.add(new Category(3,"Gorras","Grs",1));
		categories.add(new Category(4,"Mochilas","Mch",1));
		categories.add(new Category(5,"Pulseras","Pls",1));
		categories.add(new Category(6,"Carteras","Ctrs",0));
		
		return categories;
	}
}
