package com.accenture.test.airport.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enums for all the error codes and their descriptions.
 *
 */
@Getter
@AllArgsConstructor
public enum ErrorCodes {
	
	COUNTRY_CODE_NOT_FOUND("Country code is not found"),
	COUNTRY_NAME_NOT_FOUND("Country name is not found"),
	COUNTRY_CODE_AND_COUNTRY_NAME_MISSING("Either proivde countryCode or countryName in query parameter"),
	COUNTRY_CODE_NAME_COMBINATION_INVALID("Combination of countryCode and countryName is incorrect"),
	INTERNAL_SERVER_ERROR("Technical error occured");
	
	private final String errorMessage;

}
