package com.ecommerce.order.interfaces;

import com.ecommerce.order.model.OrderRequest;
import com.ecommerce.order.model.OrderResponse;

public interface OrderService {
	public Long placeOrder(OrderRequest orderReq);
	
	public OrderResponse getOrderDetails(Long orderId);
}
