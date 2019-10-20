package com.nasa.patch.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.nasa.patch.model.Company;
import com.nasa.patch.model.OrderItem;
import com.nasa.patch.repository.CompanyRepository;

public class OrderForm {
	
	@NotNull @NotEmpty
	private String companyName;
	
	@NotNull
	private int goal;
	
	private int progress;
		
	public OrderItem convert(CompanyRepository companyRepository) {
		Company company = companyRepository.findByName(companyName);
		OrderItem order = new OrderItem(company,goal);
		return order;
				
	}

	public OrderItem update(OrderItem order, CompanyRepository companyRepository) {
		Company company = companyRepository.findByName(companyName);
		order.setCompany(company);
		order.setGoal(goal);
		order.setProgress(progress);		
		return order;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}
	
	
	



}
