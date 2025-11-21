package com.product.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.api.entity.Category;
import com.product.api.entity.ShoppingCart;

@Repository
public interface RepoShoppingCart extends JpaRepository<ShoppingCart, Integer>{
	// usar queries como los de RepoCategory
}
