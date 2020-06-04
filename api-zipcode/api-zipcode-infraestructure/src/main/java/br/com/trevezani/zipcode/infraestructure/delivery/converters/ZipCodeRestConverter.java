package br.com.trevezani.zipcode.infraestructure.delivery.converters;

import br.com.trevezani.zipcode.core.ZipCode;
import br.com.trevezani.zipcode.infraestructure.delivery.rest.ZipCodeRest;
import br.com.trevezani.zipcode.infraestructure.shared.RestConverter;

public class ZipCodeRestConverter implements RestConverter<ZipCodeRest, ZipCode> {

	@Override
	public ZipCode mapToEntity(final ZipCodeRest rest) {
		return new ZipCode(rest.getPrimaryCity(), rest.getState(), rest.getType(), rest.getDecommissioned(), rest.getAreaCodes());
	}

	@Override
	public ZipCodeRest mapToRest(final ZipCode entity) {
		return new ZipCodeRest(entity.getPrimaryCity(), entity.getState(), entity.getType(), entity.getDecommissioned(), entity.getAreaCodes());
	}
	
}
