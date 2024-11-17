package com.ecommerce.order.interfaces;

import com.ecommerce.order.model.OrderRequest;

public interface OrderService {
	public Long placeOrder(OrderRequest orderReq);
	
	public void getOrderDetails(Long orderId);
}
