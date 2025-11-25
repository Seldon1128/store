package com.product.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.product.common.dto.ApiResponse;
import com.product.api.dto.DtoCategoryIn;
import com.product.api.dto.in.DtoShoppingCartIn;
import com.product.api.dto.out.DtoProductOut;
import com.product.api.dto.out.DtoShoppingCartOut;
import com.product.api.entity.Category;

public interface SvcShoppingCart {
	public ResponseEntity<ApiResponse> create(DtoShoppingCartIn in);
	public ResponseEntity<List<DtoShoppingCartOut>> getCart();
	public ResponseEntity<ApiResponse> deleteItemCart(Integer id);
	public ResponseEntity<ApiResponse> deleteAllItemsCart();
}
