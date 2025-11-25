package com.product.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.product.common.dto.ApiResponse;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;
import com.product.api.dto.in.DtoShoppingCartIn;
import com.product.api.dto.out.DtoShoppingCartOut;
import com.product.api.entity.Product;
import com.product.api.entity.ShoppingCart;
import com.product.api.repository.RepoProduct;
import com.product.api.repository.RepoShoppingCart;

@Service
public class SvcShoppingCartImp implements SvcShoppingCart{
	@Autowired
	RepoShoppingCart repo;
	
	@Autowired
	RepoProduct repoProduct;

	@Override
	public ResponseEntity<ApiResponse> create(DtoShoppingCartIn in) {
		try {
			if (in.getGtin() == null || in.getGtin().isBlank() || in.getQuantity() == null || in.getQuantity() <= 0) {
	            throw new ApiException(HttpStatus.BAD_REQUEST, "El gtin y quantity > 0 son obligatorios");
	        }
			
			Integer userId = Integer.valueOf(
				    SecurityContextHolder.getContext().getAuthentication().getName()
			);
			
			Optional<Product> productOpt = repoProduct.findByGtin(in.getGtin());
			
			if (productOpt.isEmpty()) {
			    throw new ApiException(HttpStatus.NOT_FOUND, "El producto no existe");
			}

			Product product = productOpt.get();

			if (product.getStatus() == 0) {
			    throw new ApiException(HttpStatus.BAD_REQUEST, "El producto no está disponible");
			}
			
			ShoppingCart existing = repo.findItem(userId, in.getGtin());
			
			if(existing == null) {
				if (product.getStock() < in.getQuantity()) {
				    throw new ApiException(HttpStatus.BAD_REQUEST, "Stock insuficiente");
				}
				
				repo.create(userId, in.getGtin(), in.getQuantity());
				
				return new ResponseEntity<>(new ApiResponse("El artículo se ha agregado al carrito"), HttpStatus.CREATED);
			}
			
			// Ya existe -> actualizar
			
			int nuevaCantidad = existing.getQuantity() + in.getQuantity();
			
			if(product.getStock() < nuevaCantidad)
				throw new ApiException(HttpStatus.BAD_REQUEST, "No se puede agregar más: stock insuficiente");
			
			repo.updateQty(existing.getCart_item_id(), nuevaCantidad);
			
			return new ResponseEntity<>(new ApiResponse("La cantidad del artículo se ha actualizado"), HttpStatus.OK);

			
		} catch (DataAccessException e) {
			 throw new DBAccessException(e);
		}
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
