package com.accenture.test.airport.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

/**
 * DTO class that stores Airport details for every Country.
 *
 */
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryAirportDetailsDto {

	private final String countryCode;

	private final List<AirportDetailsDto> airports;

}
