package com.ecommerce.order.model;

import com.ecommerce.order.external.model.PaymentResponse;
import com.ecommerce.order.external.model.ProductResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
	private Long orderId;
	private String orderStatus;
	private Long productId;
	private Float totalAmount;
	private Long quantity;
	private PaymentMethod paymentMethod;
	
	private ProductResponse productDtls;
	
	private PaymentResponse paymentDtls;
	
	
	
	
}
