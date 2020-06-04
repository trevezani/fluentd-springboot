package br.com.trevezani.zipcode.infraestructure.delivery.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.trevezani.zipcode.core.exception.ZipCodeNotExistException;
import br.com.trevezani.zipcode.core.usecase.GetInformationUseCase;
import br.com.trevezani.zipcode.core.usecase.GetZipInformationUseCase;
import br.com.trevezani.zipcode.infraestructure.delivery.ZipCodeController;
import br.com.trevezani.zipcode.infraestructure.delivery.converters.InformationRestConverter;
import br.com.trevezani.zipcode.infraestructure.delivery.converters.ZipCodeRestConverter;
import br.com.trevezani.zipcode.infraestructure.delivery.responses.CensusResponse;
import br.com.trevezani.zipcode.infraestructure.delivery.rest.InfoRest;
import br.com.trevezani.zipcode.infraestructure.delivery.rest.ZipCodeRest;
import br.com.trevezani.zipcode.infraestructure.shared.exceptions.CensusException;
import br.com.trevezani.zipcode.infraestructure.shared.log.LoggerExtended;

@RestController
@RequestMapping("/")
public class ZipCodeControllerImpl implements ZipCodeController {
	private final LoggerExtended log;
	
	private final GetZipInformationUseCase getZipInformationUseCase;
	private final GetInformationUseCase getInformationUseCase;

	private final ZipCodeRestConverter zipCodeRestConverter;
	private final InformationRestConverter informationRestConverter;
	
	public ZipCodeControllerImpl(final LoggerExtended log, final GetZipInformationUseCase getZipInformationUseCase, final GetInformationUseCase getInformationUseCase, 
			final ZipCodeRestConverter zipCodeRestConverter, final InformationRestConverter informationRestConverter) {
		this.log = log;
		this.getZipInformationUseCase = getZipInformationUseCase;
		this.getInformationUseCase = getInformationUseCase;
		this.zipCodeRestConverter = zipCodeRestConverter;
		this.informationRestConverter = informationRestConverter;
	}	

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CensusResponse<InfoRest> getInformation() throws CensusException {
		return new CensusResponse<>(String.valueOf(HttpStatus.OK.value()), informationRestConverter.mapToRest(getInformationUseCase.execute()));
	}
	
	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/zipcode/{zip}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CensusResponse<ZipCodeRest> getZipInformation(@PathVariable String zip) throws CensusException {
		try {
			return new CensusResponse<>(String.valueOf(HttpStatus.OK.value()), zipCodeRestConverter.mapToRest(getZipInformationUseCase.execute(zip)));
		} catch (ZipCodeNotExistException e) {
			log.info(String.format("Exception: %s [ZipCode: %s]", e.getMessage(), zip));

			return new CensusResponse<>(String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			throw new CensusException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}
	}
}
