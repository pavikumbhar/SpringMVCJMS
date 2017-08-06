package com.pavikumbhar.javaheart.service;

import java.util.Map;

import com.pavikumbhar.javaheart.model.Order;



public interface OrderRepository {

	public void putOrder(Order order);
	
	public Order getOrder(String orderId);
	
	public Map<String, Order> getAllOrders();
}
