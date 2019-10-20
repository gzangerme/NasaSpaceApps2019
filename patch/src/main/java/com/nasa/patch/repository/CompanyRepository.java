package com.nasa.patch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nasa.patch.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

	Company findByName(String companyName);

}
