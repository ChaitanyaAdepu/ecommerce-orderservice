package com.ecommerce.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.interfaces.OrderService;
import com.ecommerce.order.model.OrderRequest;


@RestController
@RequestMapping("/order")
public class OrderController {
	
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;

	@PostMapping("/place-order")
	public ResponseEntity<Long> createOrder(@RequestBody OrderRequest orderReq) {
		Long orderId =  orderService.placeOrder(orderReq);
		return new ResponseEntity<>(orderId,HttpStatus.CREATED);
	}
	@GetMapping("/{id}")
	public void getOrderById(@PathVariable Long orderId) {
		orderService.getOrderDetails(orderId);
	}
}
