package com.pavikumbhar.javaheart.service;

import java.util.Map;

import com.pavikumbhar.javaheart.model.InventoryResponse;
import com.pavikumbhar.javaheart.model.Order;

public interface OrderService {
	public void sendOrder(Order order);
	
	public void updateOrder(InventoryResponse response);
	
	public Map<String, Order> getAllOrders();
}
