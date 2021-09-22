package com.accenture.airport.dto;

import java.util.List;

import com.accenture.airport.model.Runway;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

/**
 * DTO class that stores list of Runways for every Airport with Airport ID
 *
 */
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirportDetailsDto {

	private final long airportId;

	private final List<Runway> runways;

}
