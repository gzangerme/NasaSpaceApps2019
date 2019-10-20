package com.nasa.patch.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.nasa.patch.model.Company;

public class CompanyForm {

	@NotNull @NotEmpty
	private String name;

	@NotNull @NotEmpty
	private String identity;	

	@NotNull @NotEmpty
	private String category;
	
	public Company convert() {
		Company company = new Company(name,identity, category);
		return company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	

}
