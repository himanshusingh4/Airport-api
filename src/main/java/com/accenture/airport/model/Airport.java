package com.accenture.airport.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model class for Airport data. It binds the data from CSV file by name.
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Airport {

	@CsvBindByName
	private long id;

	@CsvBindByName
	private String ident;

	@CsvBindByName
	private String type;

	@CsvBindByName
	private String name;

	@CsvBindByName
	private String lattitude_deg;

	@CsvBindByName
	private String longitude_deg;

	@CsvBindByName
	private String elevation;

	@CsvBindByName
	private String continent;

	@CsvBindByName
	private String iso_country;

	@CsvBindByName
	private String iso_region;

	@CsvBindByName
	private String muncipality;

	@CsvBindByName
	private String scheduled_service;

	@CsvBindByName
	private String gps_code;

	@CsvBindByName
	private String iata_code;

	@CsvBindByName
	private String local_code;

	@CsvBindByName
	private String home_link;

	@CsvBindByName
	private String wikipedia_link;

	@CsvBindByName
	private String keywords;

}
