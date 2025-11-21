package com.product.api.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class DtoShoppingCartIn {
	@JsonProperty("gtin")
	@Pattern(regexp = "^\\+?\\d{13}$", message = "El gtin tiene un formato inválido")
	@NotNull(message="El gtin es obligatorio")
	private String gtin;

	@JsonProperty("quantity")
	@NotNull(message="El quantity es obligatorio")
	private Integer quantity;
	
	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
