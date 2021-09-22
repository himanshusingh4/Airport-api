package com.accenture.airport.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.accenture.airport.service.AirportService;
import com.accenture.airport.service.CountryService;
import com.accenture.airport.service.RunwayService;
import com.accenture.airport.testdata.Data;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = AirportAPIController.class)
public class AirportAPIControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private AirportService airportService;

	@MockBean
	private CountryService countryService;

	@MockBean
	private RunwayService runwayService;

	@Test
	@DisplayName("Test case to verify GET runways endpoint with expected response")
	void testgetRunwaysByCountry_positive() throws Exception {

		when(countryService.getCountryCode(Mockito.anyString())).thenReturn(Optional.ofNullable("IN"));
		when(airportService.getAirportDetailsForCountry(Mockito.anyString())).thenReturn(Data.getAirportList());
		when(runwayService.getRunwaysForAirport(Mockito.anyList())).thenReturn(Data.getRunwayDetailsForAirport());

		MvcResult mvcResult = mockMvc
				.perform(get("/v1/airports/runways").param("countryName", "India").param("countryCode", "IN"))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

		String actualResponseBody = mvcResult.getResponse().getContentAsString();

		assertThat(actualResponseBody)
				.isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(Data.getCountryAirportDetailsDto()));

	}

	@Test
	@DisplayName("Test case to verify GET reports endpoint with expected response")
	void testGetAirportReportsByCountry_positive() throws Exception {

		int recordSize = 10;
		when(airportService.getNumberOfAirportsForCountry(recordSize)).thenReturn(Data.getTotalAirportsDtoList());

		MvcResult mvcResult = mockMvc
				.perform(get("/v1/airports/reports").param("recordSize", String.valueOf(recordSize)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

		String actualResponseBody = mvcResult.getResponse().getContentAsString();

		assertThat(actualResponseBody)
				.isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(Data.getTotalAirportsDtoList()));
	}

	@Test
	@DisplayName("Test case to verify 404 error message in case of wrong URI is used")
	void testGetAirportReportsByCountry_negative() throws Exception {

		mockMvc.perform(get("/v1/airports/reports/test").param("recordSize", "10")).andExpect(status().isNotFound());

	}

}
