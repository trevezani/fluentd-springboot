package br.com.trevezani.zipcode.infraestructure.shared.exceptions;

public class CensusException extends Exception {
	private static final long serialVersionUID = -4031858430590929498L;

	private final int code;

	public CensusException(final int code, final String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}
}
