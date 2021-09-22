package com.accenture.airport.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.accenture.airport.exception.TechnicalException;
import com.accenture.airport.model.Country;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.extern.log4j.Log4j2;

/**
 * Utility class for Country service.
 *
 */
@Component
@Log4j2
public class CountryUtil {

	/**
	 * This methods reads data from countries.csv present on class path and then
	 * stores it into List and returns it.
	 * 
	 * @return Returns list having details of Countries
	 */
	public List<Country> getCountriesCSVdata() {

		List<Country> countryList = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new ClassPathResource("countries.csv").getInputStream()))) {

			CsvToBean<Country> csvToBean = new CsvToBeanBuilder<Country>(reader).withType(Country.class)
					.withIgnoreLeadingWhiteSpace(true).build();

			countryList = csvToBean.parse();

		} catch (IOException | IllegalStateException ex) {

			log.error(ex.getMessage());
			throw new TechnicalException(ex.getMessage());
		}

		return countryList;
	}

}
