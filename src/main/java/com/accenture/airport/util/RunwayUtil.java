package com.accenture.airport.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.accenture.airport.exception.TechnicalException;
import com.accenture.airport.model.Runway;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.extern.log4j.Log4j2;

/**
 * Utility class for Runway service.
 *
 */
@Component
@Log4j2
public class RunwayUtil {

	/**
	 * This methods reads data from runways.csv present on class path and then
	 * stores it into List and returns it.
	 * 
	 * @return Returns list having details of Runways
	 */
	public List<Runway> getRunwaysCSVdata() {

		List<Runway> runwayList = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new ClassPathResource("runways.csv").getInputStream()))) {

			CsvToBean<Runway> csvToBean = new CsvToBeanBuilder<Runway>(reader).withType(Runway.class)
					.withIgnoreLeadingWhiteSpace(true).build();

			runwayList = csvToBean.parse();

		} catch (IOException | IllegalStateException ex) {

			log.error(ex.getMessage());
			throw new TechnicalException(ex.getMessage());
		}

		return runwayList;
	}

}
