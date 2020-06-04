package br.com.trevezani.zipcode.infraestructure.configuration;

import java.sql.Connection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.trevezani.zipcode.core.InternalBuildProperties;
import br.com.trevezani.zipcode.core.usecase.GetInformationUseCaseImpl;
import br.com.trevezani.zipcode.core.usecase.GetZipInformationUseCaseImpl;
import br.com.trevezani.zipcode.infraestructure.delivery.converters.InformationRestConverter;
import br.com.trevezani.zipcode.infraestructure.delivery.converters.ZipCodeRestConverter;
import br.com.trevezani.zipcode.infraestructure.persistence.impl.ZipCodeServiceImpl;
import br.com.trevezani.zipcode.infraestructure.shared.log.LoggerExtended;

@Configuration
public class ZipCodeConfiguration {
	@Autowired
	@Qualifier("connection")
	private Connection connection;
	
	@Autowired
	private BuildProperties buildProperties;	
	
	@Bean
	public LoggerExtended createJSONLogger() {
		return new LoggerExtended();
	}
	
	@Bean
	public ZipCodeRestConverter createZipCodeRestConverter() {
		return new ZipCodeRestConverter();
	}

	@Bean
	public InformationRestConverter createInformationRestConverter() {
		return new InformationRestConverter();
	}
	
	@Bean
	public GetZipInformationUseCaseImpl createGetZipInformationUseCase() {
		return new GetZipInformationUseCaseImpl(new ZipCodeServiceImpl(connection));
	}
	
	@Bean
	public GetInformationUseCaseImpl createGetInformationUseCase() {
		return new GetInformationUseCaseImpl(Optional.of(buildProperties).map(this::map).orElse(new InternalBuildProperties()));
	}
	
	private InternalBuildProperties map(BuildProperties buildProperties) {
		return new InternalBuildProperties(buildProperties.getName(), buildProperties.getVersion());
	}
}
