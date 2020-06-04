package br.com.trevezani.zipcode.infraestructure.delivery.converters;

import br.com.trevezani.zipcode.core.Info;
import br.com.trevezani.zipcode.infraestructure.delivery.rest.InfoRest;
import br.com.trevezani.zipcode.infraestructure.shared.RestConverter;

public class InformationRestConverter implements RestConverter<InfoRest, Info> {

	@Override
	public Info mapToEntity(final InfoRest rest) {
		return new Info(rest.getArtifact(), rest.getVersion());
	}

	@Override
	public InfoRest mapToRest(final Info entity) {
		return new InfoRest(entity.getArtifact(), entity.getVersion());
	}
	
}
