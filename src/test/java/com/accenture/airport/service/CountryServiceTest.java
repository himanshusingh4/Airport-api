package com.accenture.airport.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.accenture.airport.constant.ErrorCodes;
import com.accenture.airport.exception.BadRequestException;
import com.accenture.airport.exception.ElementNotFoundException;

@SpringBootTest
public class CountryServiceTest {

	@Autowired
	private CountryService countryService;

	@Test
	@DisplayName("Test case to verify correct country code is returned for given country name")
	void getCountryCodeTest() {

		assertTrue("NL".equalsIgnoreCase(countryService.getCountryCode("Netherlands").get()));
		assertFalse("IN".equalsIgnoreCase(countryService.getCountryCode("Netherlands").get()));

	}

	@Test
	@DisplayName("Test case to verify if country code is valid")
	void isValidCountryCodeTest() {

		assertTrue(countryService.isValidCountryCode("NL"));
		assertFalse(countryService.isValidCountryCode("NLLLL"));
	}

	@Test
	@DisplayName("Test case to verify correct exception with error message is returned in case of wrong ountry code and country name")
	void validateCountryCodeAndNameTest_CountryCodeNameInvalid() {

		BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
			countryService.validateCountryCodeAndName("IN", "Netherlands");
		});

		assertEquals(ErrorCodes.COUNTRY_CODE_NAME_COMBINATION_INVALID.getErrorMessage(),
				badRequestException.getErrorResponse().getMessage());
	}

	@Test
	@DisplayName("Test case to verify correct exception with error message is returned in case of missing country name and country name both")
	void validateCountryCodeAndNameTest_CountryCodeNameMissing() {

		BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
			countryService.validateCountryCodeAndName("", "");
		});

		assertEquals(ErrorCodes.COUNTRY_CODE_AND_COUNTRY_NAME_MISSING.getErrorMessage(),
				badRequestException.getErrorResponse().getMessage());
	}

	@Test
	@DisplayName("Test case to verify correct exception with error message is returned in case of missing ountry code")
	void validateCountryCodeAndNameTest_CountryCodeNotFound() {

		ElementNotFoundException elementNotFoundException = assertThrows(ElementNotFoundException.class, () -> {
			countryService.validateCountryCodeAndName("INA", "");
		});

		assertEquals(ErrorCodes.COUNTRY_CODE_NOT_FOUND.getErrorMessage(),
				elementNotFoundException.getErrorResponse().getMessage());
	}

	@Test
	@DisplayName("Test case to verify correct exception with error message is returned in case of missing country name")
	void validateCountryCodeAndNameTest_CountryNameNotFound() {

		ElementNotFoundException elementNotFoundException = assertThrows(ElementNotFoundException.class, () -> {
			countryService.validateCountryCodeAndName("", "Nelhs");
		});

		assertEquals(ErrorCodes.COUNTRY_NAME_NOT_FOUND.getErrorMessage(),
				elementNotFoundException.getErrorResponse().getMessage());
	}
	
	@Test
	@DisplayName("Test method to get full country name from fuzzy word")
	void fuzzySearchTest() throws ElementNotFoundException {

		assertTrue("Zimbabwe".equalsIgnoreCase(countryService.getFullCountryName("Zimba")));
		assertTrue("Netherlands".equalsIgnoreCase(countryService.getFullCountryName("Nether")));
		assertTrue("Australia".equalsIgnoreCase(countryService.getFullCountryName("ustraia")));
		assertTrue("Croatia".equalsIgnoreCase(countryService.getFullCountryName("roatia")));
		assertTrue("Spain".equalsIgnoreCase(countryService.getFullCountryName("Span")));
		assertTrue("India".equalsIgnoreCase(countryService.getFullCountryName("Indi")));
	}

}
