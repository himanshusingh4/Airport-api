package com.accenture.airport.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * DTO class that stores Total number of airports for every Country.
 *
 */
@Getter
@Builder
public class TotalAirportsDto {

	private final String countryCode;

	private final long numberOfAirports;

}
