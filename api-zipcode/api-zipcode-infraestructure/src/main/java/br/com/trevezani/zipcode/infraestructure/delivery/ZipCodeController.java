package br.com.trevezani.zipcode.infraestructure.delivery;

import br.com.trevezani.zipcode.infraestructure.delivery.responses.CensusResponse;
import br.com.trevezani.zipcode.infraestructure.delivery.rest.InfoRest;
import br.com.trevezani.zipcode.infraestructure.delivery.rest.ZipCodeRest;
import br.com.trevezani.zipcode.infraestructure.shared.exceptions.CensusException;

public interface ZipCodeController {

	CensusResponse<ZipCodeRest> getZipInformation(String zip) throws CensusException;	

	CensusResponse<InfoRest> getInformation() throws CensusException;	
	
}
