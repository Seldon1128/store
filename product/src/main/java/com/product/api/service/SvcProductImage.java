package com.product.api.service;

import org.springframework.http.ResponseEntity;

import com.product.api.commons.dto.ApiResponse;
import com.product.api.entity.ProductImage;
import com.product.api.dto.in.DtoProductImageIn;

public interface SvcProductImage {

	ResponseEntity<ApiResponse> deleteProductImage(Integer product_image_id);

	ResponseEntity<ProductImage[]> getProductImages(Integer product_id);

	ResponseEntity<ApiResponse> uploadProductImage(DtoProductImageIn in);

}
