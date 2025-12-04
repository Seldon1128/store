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

import com.product.api.dto.in.DtoProductIn;
import com.product.api.dto.in.UpdateStockRequest;
import com.product.api.dto.out.DtoProductListOut;
import com.product.api.dto.out.DtoProductOut;
import com.product.api.service.SvcProduct;
import com.product.common.dto.ApiResponse;
import com.product.exception.ApiException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
@Tag(name = "Product", description = "Catálogo de productos")
public class CtrlProduct {

	@Autowired
	SvcProduct svc;

	@GetMapping
	@Operation(summary = "Consultar productos", description = "Consulta todos los productos")
	public ResponseEntity<List<DtoProductListOut>> getProducts() {
		return svc.getProducts();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Consulta un product", description = "Consulta un producto")
	public ResponseEntity<DtoProductOut> getProduct(@PathVariable Integer id) {
		return svc.getProduct(id);
	}

	@PostMapping
	@Operation(summary = "Registrar categorias", description = "Registra un nuevo producto")
	public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody DtoProductIn in) {
		return svc.createProduct(in);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Actualizar productos", description = "Actualiza un producto")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable Integer id, @Valid @RequestBody DtoProductIn in) {
		return svc.updateProduct(id, in);
	}

	@PatchMapping("/{id}/enable")
	@Operation(summary = "Activar producto", description = "Producto disponible")
	public ResponseEntity<ApiResponse> enableProduct(@PathVariable Integer id) {
		return svc.enableProduct(id);
	}

	@PatchMapping("/{id}/disable")
	@Operation(summary = "Desactivar producto", description = "Producto no disponible")
	public ResponseEntity<ApiResponse> disableProduct(@PathVariable Integer id) {
		return svc.disableProduct(id);
	}
	
	@PutMapping("/{gtin}/update-stock")
	@Operation(summary = "Actualizar stock de producto", description = "Actualización Proucto")
	public ResponseEntity<ApiResponse> updateStock(@PathVariable String gtin, @RequestBody UpdateStockRequest request) {
	    return svc.updateStock(gtin, request);
	}
}
