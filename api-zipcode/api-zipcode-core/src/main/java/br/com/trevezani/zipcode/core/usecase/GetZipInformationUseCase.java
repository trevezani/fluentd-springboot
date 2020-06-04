package br.com.trevezani.zipcode.core.usecase;

import java.sql.SQLException;

import br.com.trevezani.zipcode.core.ZipCode;
import br.com.trevezani.zipcode.core.exception.ZipCodeNotExistException;

public interface GetZipInformationUseCase {

	public ZipCode execute(String zip) throws ZipCodeNotExistException, SQLException;
	
}
