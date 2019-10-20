package com.nasa.patch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nasa.patch.model.OrderItem;

public interface OrderRepository extends JpaRepository<OrderItem, Integer>{
	

}
