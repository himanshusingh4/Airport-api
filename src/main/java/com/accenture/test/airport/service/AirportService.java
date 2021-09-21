package com.accenture.test.airport.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.accenture.test.airport.dto.TotalAirportsDto;
import com.accenture.test.airport.model.Airport;
import com.accenture.test.airport.util.AirportUtil;

import lombok.extern.log4j.Log4j2;

/**
 * Service class to work with Airport data
 *
 */
@Log4j2
@Service
public class AirportService {

	private final AirportUtil airportUtil;

	private List<Airport> airportData;

	/**
	 * Paramaterized constructor with injection
	 * 
	 * @param airportUtil AirportUtil bean
	 */
	public AirportService(AirportUtil airportUtil) {
		this.airportUtil = airportUtil;
	}

	/**
	 * This method is used to fetch Airport data using Airport util class and store
	 * in Airport List.
	 * 
	 */
	@PostConstruct
	public void loadAirportData() {
		log.info("Loading data from airports.csv file");
		airportData = airportUtil.getAirportsCSVdata();
	}

	/**
	 * This method is used to fetch airport list for give ISO countryCode.
	 * 
	 * @param countryCode ISO country code
	 * @return List of airports
	 */
	public List<Airport> getAirportDetailsForCountry(String countryCode) {

		return airportData.stream().filter(a -> countryCode.equalsIgnoreCase(a.getIso_country()))
				.collect(Collectors.toList());

	}

	/**
	 * This method is used to calculate number of Airports for each Country and
	 * return the list in descending order of airport numbers. Output records size
	 * depends upon input param recordSize
	 * 
	 * @param recordSize Size needed for returned list
	 * @return List of TotalAirportsDto having number of Airports for each Country
	 */
	public List<TotalAirportsDto> getNumberOfAirportsForCountry(int recordSize) {

		Map<String, Long> airportDetailsMap = airportData.stream()
				.collect(Collectors.groupingBy(Airport::getIso_country, Collectors.counting()));

		List<TotalAirportsDto> totalAirportsList = new ArrayList<>();

		airportDetailsMap.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
				.limit(recordSize).forEachOrdered(e -> totalAirportsList.add(
						TotalAirportsDto.builder().countryCode(e.getKey()).numberOfAirports(e.getValue()).build()));

		return totalAirportsList;

	}

}
