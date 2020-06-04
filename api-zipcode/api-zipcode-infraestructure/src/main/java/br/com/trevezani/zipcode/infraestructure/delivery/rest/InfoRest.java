package br.com.trevezani.zipcode.infraestructure.delivery.rest;

import java.io.Serializable;

public class InfoRest implements Serializable {
	private static final long serialVersionUID = 6663355551393579645L;

	private String artifact;
	private String version;
	
	public InfoRest() {
		super();
	}
	
	public InfoRest(String artifact, String version) {
		super();
		this.artifact = artifact;
		this.version = version;
	}

	public String getArtifact() {
		return artifact;
	}

	public void setArtifact(String artifact) {
		this.artifact = artifact;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "InfoRest [artifact=" + artifact + ", version=" + version + "]";
	}
}
