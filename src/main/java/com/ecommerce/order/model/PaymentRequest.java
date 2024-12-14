package com.ecommerce.order.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PaymentRequest {

	private Long orderId;

	private String paymentMode;

	private String transactionNumber;

	private Float amount;

}
