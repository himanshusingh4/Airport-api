package com.accenture.airport.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.accenture.airport.constant.ErrorCodes;
import com.accenture.airport.exception.BadRequestException;
import com.accenture.airport.exception.ElementNotFoundException;
import com.accenture.airport.exception.ErrorResponse;
import com.accenture.airport.model.Country;
import com.accenture.airport.util.CountryUtil;

import lombok.extern.log4j.Log4j2;

/**
 * Service class to work with Country data
 *
 */
@Service
@Log4j2
public class CountryService {

	private final CountryUtil countryUtil;

	private List<Country> countryData;

	/**
	 * Paramaterized constructor with injection
	 * 
	 * @param countryUtil CountryUtil bean
	 */
	public CountryService(CountryUtil countryUtil) {
		super();
		this.countryUtil = countryUtil;
	}

	/**
	 * This method is used to fetch Country data using Country util class and store
	 * in Country List.
	 * 
	 */
	@PostConstruct
	public void loadCountryData() {
		log.info("Loading data from countries.csv file");
		countryData = countryUtil.getCountriesCSVdata();
	}

	/**
	 * This method is used to return ISO countryCode from countryName
	 * 
	 * @param countryName Full Country name
	 * @return Optional with ISO country code
	 */
	public Optional<String> getCountryCode(String countryName) {

		return countryData.stream().filter(c -> countryName.equalsIgnoreCase(c.getName())).map(c -> c.getCode())
				.findFirst();
	}

	/**
	 * This method is used to validate the Country code.
	 * 
	 * @param countryCode ISO country code
	 * @return result based on whether country code is present in Country data or
	 *         not
	 */
	public boolean isValidCountryCode(String countryCode) {

		return countryData.stream().anyMatch(c -> countryCode.equalsIgnoreCase(c.getCode()));
	}

	/**
	 * 
	 * This method is used to validate both country code, country name and also
	 * against each other if both present.
	 * 
	 * @param countryCode ISO country code
	 * @param countryName Full Country name
	 * @throws BadRequestException      Exception occurred because of bad input
	 * @throws ElementNotFoundException Exception when element is not found
	 */
	public void validateCountryCodeAndName(String countryCode, String countryName)
			throws BadRequestException, ElementNotFoundException {

		if (StringUtils.isBlank(countryCode) && StringUtils.isBlank(countryName)) {

			throw new BadRequestException(new ErrorResponse(ErrorCodes.COUNTRY_CODE_AND_COUNTRY_NAME_MISSING.name(),
					ErrorCodes.COUNTRY_CODE_AND_COUNTRY_NAME_MISSING.getErrorMessage()));

		}

		if (StringUtils.isNotBlank(countryCode) && StringUtils.isBlank(countryName)) {

			if (!isValidCountryCode(countryCode)) {
				throw new ElementNotFoundException(new ErrorResponse(ErrorCodes.COUNTRY_CODE_NOT_FOUND.name(),
						ErrorCodes.COUNTRY_CODE_NOT_FOUND.getErrorMessage()));
			}

		} else if (StringUtils.isBlank(countryCode) && StringUtils.isNotBlank(countryName)) {

			Optional<String> validCountryCode = getCountryCode(countryName);
			if (!validCountryCode.isPresent()) {
				throw new ElementNotFoundException(new ErrorResponse(ErrorCodes.COUNTRY_NAME_NOT_FOUND.name(),
						ErrorCodes.COUNTRY_NAME_NOT_FOUND.getErrorMessage()));
			}

		} else {

			Optional<String> validCountryCode = getCountryCode(countryName);
			if (validCountryCode.isPresent()) {

				if (!validCountryCode.get().equalsIgnoreCase(countryCode)) {
					throw new BadRequestException(
							new ErrorResponse(ErrorCodes.COUNTRY_CODE_NAME_COMBINATION_INVALID.name(),
									ErrorCodes.COUNTRY_CODE_NAME_COMBINATION_INVALID.getErrorMessage()));
				}

			} else {
				throw new ElementNotFoundException(new ErrorResponse(ErrorCodes.COUNTRY_NAME_NOT_FOUND.name(),
						ErrorCodes.COUNTRY_NAME_NOT_FOUND.getErrorMessage()));
			}
		}

	}

}
