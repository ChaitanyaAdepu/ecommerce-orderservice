package com.ecommerce.order.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.order.external.model.ProductResponse;


@FeignClient(name = "PRODUCT-SERVICE/product")
public interface ProductService {
	@PostMapping("/reduce-quantity/{id}")
	ResponseEntity<Object> reduceQuantity(@PathVariable("id") Long productId, 
			@RequestParam Long quantity);
	@GetMapping
	public ProductResponse getProducts(@RequestParam(required = false) Long productId);
}
