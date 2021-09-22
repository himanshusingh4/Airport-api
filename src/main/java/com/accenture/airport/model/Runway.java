package com.accenture.airport.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model class for Runway data. It binds the data from CSV file by name.
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Runway {

	@CsvBindByName
	private long id;

	@CsvBindByName
	private long airport_ref;

	@CsvBindByName
	private String airport_ident;

	@CsvBindByName
	private long length_ft;

	@CsvBindByName
	private long width_ft;

	@CsvBindByName
	private String surface;

	@CsvBindByName
	private String lighted;

	@CsvBindByName
	private String closed;

	@CsvBindByName
	private String le_ident;

	@CsvBindByName
	private String le_latitude_deg;

	@CsvBindByName
	private String le_longitude_deg;

	@CsvBindByName
	private String le_elevation_ft;

	@CsvBindByName
	private String le_heading_degT;

	@CsvBindByName
	private String le_displaced_threshold_ft;

	@CsvBindByName
	private String he_ident;

	@CsvBindByName
	private String he_latitude_deg;

	@CsvBindByName
	private String he_longitude_deg;

	@CsvBindByName
	private String he_elevation_ft;
	
	@CsvBindByName
	private String he_heading_degT;
	
	@CsvBindByName
	private String he_displaced_threshold_ft;

}
