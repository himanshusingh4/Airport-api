package com.accenture.airport.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.accenture.airport.model.Airport;
import com.accenture.airport.util.AirportUtil;

@SpringBootTest
public class AirportUtilTest {

	@Autowired
	AirportUtil airportUtil;

	@ParameterizedTest
	@ValueSource(strings = {"IN", "NL", "us"})
	@DisplayName("Test case to verify data is loaded correctly from airports.csv file")
	void getAirportCSVDataTest_positive(String countryCode) {

		List<Airport> airportList = airportUtil.getAirportsCSVdata();
		assertFalse(airportList.isEmpty());
		assertTrue(airportList.stream().anyMatch(a -> countryCode.equalsIgnoreCase(a.getIso_country())));

	}
	
	@ParameterizedTest
	@ValueSource(strings = {"I", "NdL", "usss"})
	@DisplayName("Test case to verify data is loaded correctly from airports.csv file")
	void getAirportCSVDataTest_negative(String countryCode) {

		List<Airport> airportList = airportUtil.getAirportsCSVdata();
		assertFalse(airportList.isEmpty());
		assertFalse(airportList.stream().anyMatch(a -> countryCode.equalsIgnoreCase(a.getIso_country())));

	}

}
