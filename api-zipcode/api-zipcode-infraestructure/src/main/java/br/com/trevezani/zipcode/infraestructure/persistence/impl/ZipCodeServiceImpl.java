package br.com.trevezani.zipcode.infraestructure.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import br.com.trevezani.zipcode.core.ZipCode;
import br.com.trevezani.zipcode.core.ports.ZipCodeRepositoryService;

public class ZipCodeServiceImpl implements ZipCodeRepositoryService {
	private final Connection connection;
	
	public ZipCodeServiceImpl(final Connection connection) {
		this.connection = connection;
	}	
	
	@Override
	public Optional<ZipCode> findByZip(String zip) throws SQLException {
		Optional<ZipCode> zipinfo = Optional.empty();
		
		String sql = String.format("SELECT primary_city, state, type, decommissioned, area_codes FROM zip_code_database WHERE zip like '%s'", zip);
		
	    try (PreparedStatement stmt = connection.prepareStatement(sql);
	    	 ResultSet rs = stmt.executeQuery()) {
	    	
	        while (rs.next()) {
            	String primary_city = rs.getString("primary_city");
            	String state = rs.getString("state");
            	String type = rs.getString("type");
            	String decommissioned = rs.getString("decommissioned");
            	String area_codes = rs.getString("area_codes");
            	
            	zipinfo = Optional.of(new ZipCode(primary_city, state, type, decommissioned, area_codes));
	        }
	        
	    }
		
		return zipinfo;
	}
}
