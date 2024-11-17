package com.ecommerce.order.service;

import java.sql.Timestamp;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.order.dao.OrderRepo;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.external.client.ProductService;
import com.ecommerce.order.interfaces.OrderService;
import com.ecommerce.order.model.OrderRequest;

@Service
public class OrderServiceImpl implements OrderService{

	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	private ProductService productService;

	@Override
	public Long placeOrder(OrderRequest orderReq) {
		
		//reduce the quantity
		productService.reduceQuantity(orderReq.getProductId(), orderReq.getQuantity());
		
		Order order = new Order();
		order.setAmount(orderReq.getTotalAmount());
		order.setQuantity(orderReq.getQuantity());
		order.setOrderStatus("CREATED");
		order.setOrderedOn(Timestamp.from(Instant.now()));
		order.setProductId(orderReq.getProductId());
		
		Long orderId = orderRepo.save(order).getId();
		
		return orderId;
	}

	@Override
	public void getOrderDetails(Long orderId) {
		
	}
	
}
