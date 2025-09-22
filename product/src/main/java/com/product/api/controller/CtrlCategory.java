package com.product.api.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.service.SvcCategory; 

@RestController
@RequestMapping("/category")
public class CtrlCategory {
	@Autowired
	SvcCategory svc;
	
	@GetMapping()
	public List<Category> findAll(){
		return svc.findAll();
	}
	
	
	@GetMapping("/active")
	public List<Category> findActive(){
		return svc.findActive();
	}
	
	@PostMapping()
	public List<Category> create(DtoCategoryIn in){
		//throw new ApiException(HttpStatus.BAD_REQUEST,...
		return svc.create(in);
	}
	
	@PutMapping("/{id}")
	public List<Category> update(DtoCategoryIn in, int id){
		return svc.update(in, id);
	}
	
	@PatchMapping("/{id}/enable")
	public List<Category> enable(int id){
		return svc.enable(id);
	}
	
	@PatchMapping("/{id}/disable")
	public List<Category> disable(int id){
		return svc.disable(id);
	}
	
	
	
	
}
