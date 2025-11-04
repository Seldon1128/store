package com.product.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.commons.dto.ApiResponse;
import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid; 

@RestController
@RequestMapping("/category")
@Tag(name = "Category", description = "Catálogo de categorias")
public class CtrlCategory {
	@Autowired
	SvcCategory svc;
	
	@GetMapping()
	@Operation(summary = "Consultar categorias", description = "Consulta todas las categorias registradas")
	public ResponseEntity<List<Category>> findAll(){
		return ResponseEntity.ok(svc.findAll());
	}
	
	
	@GetMapping("/active")
	@Operation(summary = "Consultar categorias", description = "Consulta todas las categorias activas")
	public ResponseEntity<List<Category>> findActive(){
		return ResponseEntity.ok(svc.findActive());
	}
	
	@PostMapping()
	@Operation(summary = "Registras categorias", description = "Registra una nueva categoria")
	public ResponseEntity<ApiResponse> create( @Valid @RequestBody DtoCategoryIn in){
		//throw new ApiException(HttpStatus.BAD_REQUEST,...   
		return ResponseEntity.ok(svc.create(in));
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Actualizar categorias", description = "Actualiza una categoria")
	public ResponseEntity<ApiResponse> update(@Valid @RequestBody DtoCategoryIn in,
            @PathVariable("id") Integer id){
		return ResponseEntity.ok(svc.update(in, id));
	}
	
	@PatchMapping("/{id}/enable")
	@Operation(summary = "Activar categorias", description = "Activa categoria desactivada")
	public ResponseEntity<ApiResponse> enable(@PathVariable Integer id){
		return ResponseEntity.ok(svc.enable(id));
	}
	
	@PatchMapping("/{id}/disable")
	@Operation(summary = "Desactivar categorias", description = "Desactiva categoria registrada")
	public ResponseEntity<ApiResponse> disable(@PathVariable Integer id){
		return ResponseEntity.ok(svc.disable(id));
	}
	
	
	
	
}
