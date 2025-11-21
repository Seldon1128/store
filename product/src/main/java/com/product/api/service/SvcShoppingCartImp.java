package com.product.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.common.dto.ApiResponse;
import com.product.api.dto.in.DtoShoppingCartIn;
import com.product.api.dto.out.DtoShoppingCartOut;
import com.product.api.repository.RepoShoppingCart;

@Service
public class SvcShoppingCartImp implements SvcShoppingCart{
	@Autowired
	RepoShoppingCart repo;

	@Override
	public ResponseEntity<ApiResponse> create(DtoShoppingCartIn in) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<DtoShoppingCartOut> getCart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ApiResponse> deleteItemCart(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ApiResponse> deleteAllItemsCart(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
