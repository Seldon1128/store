package com.product.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.dto.out.DtoProductOut;
import com.product.api.entity.Product;

@Repository
public interface RepoProduct extends JpaRepository<Product, Integer> {
	
	@Query("SELECT new com.product.api.dto.out.DtoProductOut(" +
	         "p.product_id, p.gtin, p.product, p.description, p.price, p.stock, p.category_id, p.status) " +
	         "FROM Product p WHERE p.product_id = :id")
	  Optional<DtoProductOut> findDtoById(@Param("id") Integer id);
	
}
