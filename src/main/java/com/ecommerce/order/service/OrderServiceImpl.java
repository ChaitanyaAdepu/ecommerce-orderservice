package com.ecommerce.order.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.commonlib.aspect.Loggable;
import com.ecommerce.order.dao.OrderRepo;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.exception.AppServiceException;
import com.ecommerce.order.external.client.PaymentService;
import com.ecommerce.order.external.client.ProductService;
import com.ecommerce.order.external.model.PaymentResponse;
import com.ecommerce.order.external.model.ProductResponse;
import com.ecommerce.order.interfaces.OrderService;
import com.ecommerce.order.model.OrderRequest;
import com.ecommerce.order.model.OrderResponse;
import com.ecommerce.order.model.PaymentRequest;

@Service
public class OrderServiceImpl implements OrderService{

	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private PaymentService payService;

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
		
		PaymentRequest payReq = PaymentRequest.builder()
				                .amount(order.getAmount())
				                .orderId(order.getId())
				                .paymentMode(orderReq.getPaymentMethod().name())
				                .build();
		//payment
		payService.doPayment(payReq);
		
		return orderId;
	}

	@Override
	@Loggable
	public OrderResponse getOrderDetails(Long orderId) {
//		if(orderId == null) {
//			return getAllOrders();
//		}
		Optional<Order> orderOpt = orderRepo.findById(orderId);
		if(!orderOpt.isPresent()) {
			throw new AppServiceException("OrderID not found", "ERR404", 404);
		}
		OrderResponse resp = new OrderResponse();
		resp.setOrderId(orderOpt.get().getId());
		resp.setOrderStatus(orderOpt.get().getOrderStatus());
		resp.setProductId(orderOpt.get().getProductId());
		resp.setTotalAmount(orderOpt.get().getAmount());
		resp.setQuantity(orderOpt.get().getQuantity());
		
		log.info("set product details");
		ProductResponse apiResp = productService.getProducts(resp.getProductId());
		resp.setProductDtls(apiResp);
		
		PaymentResponse paymentDtls = payService.getPaymentDetailsByOrderId(orderId);
		resp.setPaymentDtls(paymentDtls);
		return resp;
	}
	
}
