package com.ecommerce.order.external.model;

import java.sql.Timestamp;

import com.ecommerce.order.model.OrderResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentResponse {
	
	private Long orderId;

	private String paymentMode;

	private String transactionNumber;

	private Float amount;

	private Timestamp paymentDate;
	
	private String status;
	
}