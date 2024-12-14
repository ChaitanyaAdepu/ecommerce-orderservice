package com.ecommerce.order.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.order.exception.AppServiceException;
import com.ecommerce.order.external.model.PaymentResponse;
import com.ecommerce.order.model.PaymentRequest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@CircuitBreaker(name="external",fallbackMethod="fallback")
@FeignClient(name = "PaymentService/payment")
public interface PaymentService {
	@PostMapping
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest payload);
	
	@GetMapping("/{orderid}")
	public PaymentResponse getPaymentDetailsByOrderId(@PathVariable("orderid") Long orderId);
	
	default void fallback(Exception e) {
		throw new AppServiceException("Payment service is not available!","ERR503",503);
	}
}
