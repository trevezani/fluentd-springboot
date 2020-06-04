package br.com.trevezani.zipcode.core.ports;

import java.sql.SQLException;
import java.util.Optional;

import br.com.trevezani.zipcode.core.ZipCode;

public interface ZipCodeRepositoryService {

	public Optional<ZipCode> findByZip(final String zip) throws SQLException;
	
}
