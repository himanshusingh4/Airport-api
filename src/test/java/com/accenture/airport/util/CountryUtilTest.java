package com.accenture.airport.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.accenture.airport.model.Country;
import com.accenture.airport.util.CountryUtil;

@SpringBootTest
public class CountryUtilTest {

	@Autowired
	CountryUtil countryUtil;

	@ParameterizedTest
	@ValueSource(strings = {"INDIA", "NETHERLANDS", "BELGIUM", "SPAIN"})
	@DisplayName("Test case to verify data is loaded correctly from countries.csv file")
	void getCountryCSVDataTest_positive(String countryName) {

		List<Country> countryList = countryUtil.getCountriesCSVdata();
		assertFalse(countryList.isEmpty());
		assertTrue(countryList.stream().anyMatch(c -> countryName.equalsIgnoreCase(c.getName())));

	}
	
	@ParameterizedTest
	@ValueSource(strings = {"INA", "NERLANDS", "BEIUM", "AIN"})
	@DisplayName("Test case to verify data is loaded correctly from counties.csv file")
	void getCountryCSVDataTest_negative(String countryName) {

		List<Country> countryList = countryUtil.getCountriesCSVdata();
		assertFalse(countryList.isEmpty());
		assertFalse(countryList.stream().anyMatch(c -> countryName.equalsIgnoreCase(c.getName())));

	}

}
