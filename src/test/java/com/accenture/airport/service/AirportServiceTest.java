package com.accenture.airport.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.accenture.airport.dto.TotalAirportsDto;
import com.accenture.airport.model.Airport;

@SpringBootTest
public class AirportServiceTest {

	@Autowired
	private AirportService airportService;

	@Test
	@DisplayName("Test case to verify list of airports are fetched correctly from airport service class")
	void getAirportDetailsForCountryTest() {

		List<Airport> airportList = airportService.getAirportDetailsForCountry("IN");

		assertTrue(airportList.size() == 342);
		assertTrue(airportList.stream().filter(a -> !"IN".equalsIgnoreCase(a.getIso_country()))
				.collect(Collectors.toList()).size() == 0);

	}

	@ParameterizedTest
	@ValueSource(ints= {10,15,20})
	@DisplayName("Test case to verify number of airports with given record size is fetched correctly")
	void getNumberOfAirportsForCountryTest(int recordSize) {

		List<TotalAirportsDto> totalAirportsList = airportService.getNumberOfAirportsForCountry(recordSize);

		assertTrue(totalAirportsList.size() == recordSize);
		assertTrue("US".equalsIgnoreCase(totalAirportsList.get(0).getCountryCode()));
		assertTrue(totalAirportsList.get(0).getNumberOfAirports() == 23820);

	}

}
