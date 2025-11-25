package com.product.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@Operation(summary = "Agregar artículo al carrito", description = "AAgrega un nuevo producto al carrito o incrementa la cantidad si ya existe")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody DtoShoppingCartIn in){
		return svc.create(in);
	}
	
	@GetMapping
	@Operation(summary = "Consultar carrito", description = "Devuelve la lista de productos agregados al carrito del usuario")
	public ResponseEntity<List<DtoShoppingCartOut>> getCart(){
		return svc.getCart();
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar un artículo del carrito", description = "Elimina un artículo específico del carrito usando su ID")
	public ResponseEntity<ApiResponse> deleteItemCart(@PathVariable Integer id){
		return svc.deleteItemCart(id);
	}
	
	@DeleteMapping
	@Operation(summary = "Vaciar carrito", description = "Elimina todos los artículos del carrito del usuario")
	public ResponseEntity<ApiResponse> deleteAllItemsCart(){
		return svc.deleteAllItemsCart();
	}
	
	
	
	
	


}
