package com.nasa.patch.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.nasa.patch.form.OrderForm;
import com.nasa.patch.model.OrderItem;
import com.nasa.patch.repository.CompanyRepository;
import com.nasa.patch.repository.OrderRepository;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@GetMapping
	public Page<OrderItem> findAll(@PageableDefault(sort="id", size=5, direction=Direction.ASC) Pageable pagination) {
		Page<OrderItem> orders = orderRepository.findAll(pagination);
		return orders;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderItem> findById(@PathVariable int id){
		Optional<OrderItem> optional = orderRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<OrderItem> create(@RequestBody @Valid OrderForm orderForm, UriComponentsBuilder uriBuilder) {
		OrderItem order = orderForm.convert(companyRepository);
		orderRepository.save(order);
		
		URI uri = uriBuilder.path("/orders/{id}").buildAndExpand(order.getId()).toUri();
		return ResponseEntity.created(uri).body(order);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<OrderItem> update(@PathVariable int id, @RequestBody @Valid OrderForm orderForm){
		Optional<OrderItem> optional = orderRepository.findById(id);
		if(optional.isPresent()) {
			OrderItem order = orderForm.update(optional.get(), companyRepository);			
			return ResponseEntity.ok(order);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable int id){
		Optional<OrderItem> optional = orderRepository.findById(id);
		if(optional.isPresent()) {
			orderRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}
