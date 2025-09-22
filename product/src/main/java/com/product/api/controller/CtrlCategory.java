package com.product.api.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.product.api.entity.Category;
import com.product.api.service.SvcCategory; 

@RestController
@RequestMapping("/category")
public class CtrlCategory {
	@Autowired
	SvcCategory svc;
	
	
	@GetMapping
	public ResponseEntity<List<Category>> getCategories(){
		//Lista de categorias
		//List<Category> categories = new ArrayList<Category>();
		//categories.add(new Category(1,"Lentes","Lts",1));
		//categories.add(new Category(2,"Relojes","Rljs",1));
		
		//return categories;
		return svc.getCategories();
	}
}
