package com.accenture.test.airport.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.accenture.test.airport.dto.AirportDetailsDto;
import com.accenture.test.airport.model.Airport;
import com.accenture.test.airport.model.Runway;
import com.accenture.test.airport.util.RunwayUtil;

import lombok.extern.log4j.Log4j2;

/**
 * Service class to work with Runway data
 *
 */
@Service
@Log4j2
public class RunwayService {

	private final RunwayUtil runwayUtil;

	private List<Runway> runwayData;

	/**
	 * Paramaterized constructor with injection
	 * 
	 * @param runwayUtil RunwayUtil bean
	 */
	public RunwayService(RunwayUtil runwayUtil) {
		super();
		this.runwayUtil = runwayUtil;
	}

	/**
	 * This method is used to fetch Runway data and store in Runway List.
	 * 
	 */
	@PostConstruct
	public void loadRunwayData() {
		log.info("Loading data from runways.csv file");
		runwayData = runwayUtil.getRunwaysCSVdata();
	}

	/**
	 * This method is used to fetch runway details for each Airport present in input
	 * Airport list
	 * 
	 * @param airportList Input Airport list
	 * @return Details of Runway for each airport.
	 */
	public List<AirportDetailsDto> getRunwaysForAirport(List<Airport> airportList) {

		Set<Long> airportIds = airportList.stream().map(a -> a.getId()).collect(Collectors.toSet());

		Map<Long, List<Runway>> runwayMap = runwayData.stream().filter(r -> airportIds.contains(r.getAirport_ref()))
				.collect(Collectors.groupingBy(Runway::getAirport_ref));

		List<AirportDetailsDto> airportDetailsDtoList = new ArrayList<>();

		for (Map.Entry<Long, List<Runway>> entry : runwayMap.entrySet()) {

			AirportDetailsDto runwayDetailsDto = AirportDetailsDto.builder().airportId(entry.getKey())
					.runways(entry.getValue()).build();

			airportDetailsDtoList.add(runwayDetailsDto);
		}

		return airportDetailsDtoList;

	}

}
