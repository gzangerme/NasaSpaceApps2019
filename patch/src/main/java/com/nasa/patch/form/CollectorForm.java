package com.nasa.patch.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.nasa.patch.model.Collector;

public class CollectorForm {
	
	@NotNull @NotEmpty
	private String name;

	@NotNull @NotEmpty
	private String identity;	

	@NotNull @NotEmpty
	private String phone;
	
	public Collector convert() {
		Collector collector = new Collector(name,identity, phone);
		return collector;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}
