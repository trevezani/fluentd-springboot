package br.com.trevezani.zipcode.core;

import java.io.Serializable;

public class Info implements Serializable {
	private static final long serialVersionUID = 2642711326848759976L;

	private String artifact;
	private String version;
	
	public Info() {
		super();
	}
	
	public Info(String artifact, String version) {
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
