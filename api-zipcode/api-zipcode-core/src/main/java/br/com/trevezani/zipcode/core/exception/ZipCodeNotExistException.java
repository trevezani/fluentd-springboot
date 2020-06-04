package br.com.trevezani.zipcode.core.exception;

public class ZipCodeNotExistException extends Exception {
	private static final long serialVersionUID = 20771739340925236L;
	
	public String getMessage() {
		return "Zip not found";
	}
}
