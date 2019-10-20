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

import com.nasa.patch.form.CompanyForm;
import com.nasa.patch.model.Company;
import com.nasa.patch.repository.CompanyRepository;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Company> create(@RequestBody @Valid CompanyForm companyForm, UriComponentsBuilder uriBuilder) {
		Company company = companyForm.convert();
		companyRepository.save(company);
		
		URI uri = uriBuilder.path("/companies/{id}").buildAndExpand(company.getId()).toUri();
		return ResponseEntity.created(uri).body(company);
	}

}
