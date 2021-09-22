package com.accenture.airport.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.accenture.airport.exception.TechnicalException;
import com.accenture.airport.model.Airport;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.extern.log4j.Log4j2;

/**
 * Utility class for Airport service.
 *
 */
@Component
@Log4j2
public class AirportUtil {

	/**
	 * This methods reads data from airport.csv present on class path and then
	 * stores it into List and returns it.
	 * 
	 * @return Returns list having details of Airports
	 */
	public List<Airport> getAirportsCSVdata() {

		List<Airport> airportList = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new ClassPathResource("airports.csv").getInputStream()))) {

			CsvToBean<Airport> csvToBean = new CsvToBeanBuilder<Airport>(reader).withType(Airport.class)
					.withIgnoreLeadingWhiteSpace(true).build();

			airportList = csvToBean.parse();

		} catch (IOException | IllegalStateException ex) {

			log.error(ex.getMessage());
			throw new TechnicalException(ex.getMessage());
		}

		return airportList;
	}

}
