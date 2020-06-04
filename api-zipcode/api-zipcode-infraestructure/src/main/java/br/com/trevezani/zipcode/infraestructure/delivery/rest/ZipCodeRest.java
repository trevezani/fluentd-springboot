package br.com.trevezani.zipcode.infraestructure.delivery.rest;

import java.io.Serializable;

public class ZipCodeRest implements Serializable {
	private static final long serialVersionUID = 7459885047164149123L;

	private String primaryCity;
	private String state;
	private String type;
	private String decommissioned;
	private String areaCodes;

	public ZipCodeRest() {
		
	}

	public ZipCodeRest(String primaryCity, String state, String type, String decommissioned, String areaCodes) {
		super();
		this.primaryCity = primaryCity;
		this.state = state;
		this.type = type;
		this.decommissioned = decommissioned;
		this.areaCodes = areaCodes;
	}

	public String getPrimaryCity() {
		return primaryCity;
	}

	public void setPrimaryCity(String primaryCity) {
		this.primaryCity = primaryCity;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDecommissioned() {
		return decommissioned;
	}

	public void setDecommissioned(String decommissioned) {
		this.decommissioned = decommissioned;
	}

	public String getAreaCodes() {
		return areaCodes;
	}

	public void setAreaCodes(String areaCodes) {
		this.areaCodes = areaCodes;
	}

	@Override
	public String toString() {
		return "ZipCodeRest [primaryCity=" + primaryCity + ", state=" + state + ", type=" + type + ", decommissioned="
				+ decommissioned + ", areaCodes=" + areaCodes + "]";
	}
}
