package com.accenture.test.airport.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.accenture.test.airport.model.Country;

@SpringBootTest
public class CountryUtilTest {

	@Autowired
	CountryUtil countryUtil;

	@ParameterizedTest
	@ValueSource(strings = {"INDIA", "NETHERLANDS", "BELGIUM", "SPAIN"})
	void getCountryCSVDataTest_positive(String countryName) {

		List<Country> countryList = countryUtil.getCountriesCSVdata();
		assertFalse(countryList.isEmpty());
		assertTrue(countryList.stream().anyMatch(c -> countryName.equalsIgnoreCase(c.getName())));

	}
	
	@ParameterizedTest
	@ValueSource(strings = {"INA", "NERLANDS", "BEIUM", "AIN"})
	void getCountryCSVDataTest_negative(String countryName) {

		List<Country> countryList = countryUtil.getCountriesCSVdata();
		assertFalse(countryList.isEmpty());
		assertFalse(countryList.stream().anyMatch(c -> countryName.equalsIgnoreCase(c.getName())));

	}

}
