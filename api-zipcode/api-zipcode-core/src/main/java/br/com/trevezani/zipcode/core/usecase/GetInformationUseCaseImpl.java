package br.com.trevezani.zipcode.core.usecase;

import br.com.trevezani.zipcode.core.Info;
import br.com.trevezani.zipcode.core.InternalBuildProperties;

public class GetInformationUseCaseImpl implements GetInformationUseCase {
	private final InternalBuildProperties buildProperties;
	
	public GetInformationUseCaseImpl(final InternalBuildProperties buildProperties) {
		this.buildProperties = buildProperties;
	}	

	@Override
	public Info execute() {
		return new Info(buildProperties.getName(), buildProperties.getVersion());
	}

}
