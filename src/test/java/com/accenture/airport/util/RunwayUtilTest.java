package com.accenture.airport.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.accenture.airport.model.Runway;
import com.accenture.airport.util.RunwayUtil;

@SpringBootTest
public class RunwayUtilTest {
	
	@Autowired
	RunwayUtil runwayUtil;

	@ParameterizedTest
	@ValueSource(ints = {255155, 250414, 265040})
	@DisplayName("Test case to verify data is loaded correctly from runways.csv file")
	void getAirportCSVDataTest_positive(int runwayId ) {

		List<Runway> runwayList = runwayUtil.getRunwaysCSVdata();
		assertFalse(runwayList.isEmpty());
		assertTrue(runwayList.stream().anyMatch(r -> runwayId == r.getId()));

	}
	
	@ParameterizedTest
	@ValueSource(ints = {255, 24, 20})
	@DisplayName("Test case to verify data is loaded correctly from runways.csv file")
	void getAirportCSVDataTest_negative(int runwayId ) {

		List<Runway> runwayList = runwayUtil.getRunwaysCSVdata();
		assertFalse(runwayList.isEmpty());
		assertFalse(runwayList.stream().anyMatch(r -> runwayId == r.getId()));

	}

}
