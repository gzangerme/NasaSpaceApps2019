package com.nasa.patch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasa.patch.model.OrderItem;
import com.nasa.patch.repository.OrderRepository;

@Service
public class DeliveryService {

	@Autowired
	private OrderRepository orderRepository;
	
	public void deliveryTrash(int quantity, OrderItem order) {
		
		order.setProgress(order.getProgress()+quantity);
		orderRepository.save(order);
	
	}
	
}
