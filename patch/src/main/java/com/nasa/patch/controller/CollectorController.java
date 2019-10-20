package com.nasa.patch.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.nasa.patch.form.CollectorForm;
import com.nasa.patch.model.Collector;
import com.nasa.patch.repository.CollectorRepository;

@RestController
@RequestMapping("/collectors")
public class CollectorController {
	
	@Autowired
	private CollectorRepository collectorRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Collector> create(@RequestBody @Valid CollectorForm collectorForm, UriComponentsBuilder uriBuilder) {
		Collector collector = collectorForm.convert();
		collectorRepository.save(collector);
		
		URI uri = uriBuilder.path("/collectors/{id}").buildAndExpand(collector.getId()).toUri();
		return ResponseEntity.created(uri).body(collector);
	}
}
