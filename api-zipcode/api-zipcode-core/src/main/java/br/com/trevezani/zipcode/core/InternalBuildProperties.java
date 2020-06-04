package br.com.trevezani.zipcode.core;

public class InternalBuildProperties {
	private String name;
	private String version;
	
	public InternalBuildProperties() {
		
	}

	public InternalBuildProperties(String name, String version) {
		super();
		this.name = name;
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
