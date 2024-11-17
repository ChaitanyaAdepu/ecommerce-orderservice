package com.ecommerce.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.order.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long>{
	
}
