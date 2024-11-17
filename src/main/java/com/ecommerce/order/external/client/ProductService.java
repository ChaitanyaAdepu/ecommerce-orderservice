package com.ecommerce.order.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "PRODUCT-SERVICE/product")
public interface ProductService {
	@PostMapping("/reduce-quantity/{id}")
	ResponseEntity<Object> reduceQuantity(@PathVariable("id") Long productId, 
			@RequestParam Long quantity);
}
