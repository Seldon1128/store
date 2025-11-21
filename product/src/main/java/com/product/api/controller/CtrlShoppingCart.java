package com.product.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.in.DtoShoppingCartIn;
import com.product.api.dto.out.DtoShoppingCartOut;
import com.product.api.service.SvcShoppingCart;
import com.product.common.dto.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cart-item")
@Tag(name = "Shopping Cart", description = "Carrito de compras")
public class CtrlShoppingCart {
	@Autowired
	SvcShoppingCart svc;
	
	@PostMapping
	@Operation(summary = "Agregar item", description = "Agrega un producto al carrito de compras")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody DtoShoppingCartIn in){
		return svc.create(in);
	}
	
	@GetMapping
	@Operation(summary = "Agregar item", description = "Agrega un producto al carrito de compras")
	public ResponseEntity<DtoShoppingCartOut> getCart(){
		return svc.getCart();
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Agregar item", description = "Agrega un producto al carrito de compras")
	public ResponseEntity<ApiResponse> deleteItemCart(Integer id){
		return svc.deleteItemCart(id);
	}
	
	@DeleteMapping
	@Operation(summary = "Agregar item", description = "Agrega un producto al carrito de compras")
	public ResponseEntity<ApiResponse> deleteAllItemsCart(Integer id){
		return svc.deleteAllItemsCart(id);
	}
	
	
	
	
	
	


}
