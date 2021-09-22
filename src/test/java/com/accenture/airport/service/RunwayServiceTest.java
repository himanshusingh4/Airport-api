package com.accenture.airport.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.accenture.airport.dto.AirportDetailsDto;
import com.accenture.airport.testdata.Data;

@SpringBootTest
public class RunwayServiceTest {

	@Autowired
	private RunwayService runwayService;

	@Test
	@DisplayName("Test case to verify all the runways for each airport is returned")
	void getRunwaysForAirportTest() {

		List<AirportDetailsDto> airportDetailsDtoList = runwayService.getRunwaysForAirport(Data.getAirportList());

		assertTrue(airportDetailsDtoList.size() > 1);
		assertTrue(airportDetailsDtoList.get(0).getAirportId() == 2513);
		assertTrue(airportDetailsDtoList.get(0).getRunways().size() == 6);

	}

}
