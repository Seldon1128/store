package com.product.api.dto.out;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_item")
public class DtoShoppingCartOut {
	@Id
	private Integer cart_item_id;

	// product y price son de tabla product 
	// talvez se tenga que hacer un join en RepoShoppingCart para obtener los datos
	private String product;
	
	private Float price;
	
	private Integer quantity;
	
	public Integer getCart_item_id() {
		return cart_item_id;
	}

	public void setCart_item_id(Integer cart_item_id) {
		this.cart_item_id = cart_item_id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
