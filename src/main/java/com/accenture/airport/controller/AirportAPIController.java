package com.accenture.airport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.airport.dto.CountryAirportDetailsDto;
import com.accenture.airport.dto.TotalAirportsDto;
import com.accenture.airport.exception.BadRequestException;
import com.accenture.airport.exception.ElementNotFoundException;
import com.accenture.airport.model.Airport;
import com.accenture.airport.service.AirportService;
import com.accenture.airport.service.CountryService;
import com.accenture.airport.service.RunwayService;

import io.micrometer.core.instrument.util.StringUtils;

/**
 * Controller class for Airport API. It process incoming requests, handle user
 * input and calls appropriate service class
 *
 */
@RestController
@RequestMapping("/v1")
public class AirportAPIController {

	private final AirportService airportService;
	private final CountryService countryService;
	private final RunwayService runwayService;

	/**
	 * Paramaterized constructor with injection
	 * 
	 * @param airportService Airport service class bean
	 * @param countryService Country service class bean
	 * @param runwayService  Runway service class bean
	 */
	@Autowired
	public AirportAPIController(AirportService airportService, CountryService countryService,
			RunwayService runwayService) {
		this.airportService = airportService;
		this.countryService = countryService;
		this.runwayService = runwayService;
	}

	/**
	 * Returns the Runway details for each airport for given country code or country
	 * name.
	 * 
	 * @param countryCode ISO country code given in query parameter
	 * @param countryName Country name given in query parameter
	 * @return Response entity giving Runway details for each airport
	 * @throws BadRequestException      Exception occurred because of bad input
	 * @throws ElementNotFoundException Exception when element is not found
	 */
	@GetMapping("/airports/runways")
	public ResponseEntity<CountryAirportDetailsDto> getRunwaysByCountry(
			@RequestParam(name = "countryCode", required = false) String countryCode,
			@RequestParam(name = "countryName", required = false) String countryName)
			throws BadRequestException, ElementNotFoundException {

		countryService.validateCountryCodeAndName(countryCode, countryName);

		if (StringUtils.isBlank(countryCode)) {
			countryCode = countryService.getCountryCode(countryName).get();
		}
		List<Airport> airportList = airportService.getAirportDetailsForCountry(countryCode);
		CountryAirportDetailsDto countryAirportDetailsDto = CountryAirportDetailsDto.builder().countryCode(countryCode)
				.airports(runwayService.getRunwaysForAirport(airportList)).build();

		return ResponseEntity.ok().body(countryAirportDetailsDto);
	}

	/**
	 * Returns the total number of airports for each country sorted in descending
	 * order. By default it returns top 10 Countries with most number of airports or
	 * else size of output depends upon recordSize if given.
	 * 
	 * @param recordSize It gives record size that needs to be returned in output
	 * @return Response with total number of airports for each country
	 */
	@GetMapping("/airports/reports")
	public ResponseEntity<List<TotalAirportsDto>> getAirportReportsByCountry(
			@RequestParam(name = "recordSize", defaultValue = "10", required = false) int recordSize) {

		List<TotalAirportsDto> totalAirportsList = airportService.getNumberOfAirportsForCountry(recordSize);

		return ResponseEntity.ok().body(totalAirportsList);
	}

}
