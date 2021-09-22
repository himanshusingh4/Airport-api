package com.accenture.airport.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model class for Country data. It binds the data from CSV file by name.
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Country {

	@CsvBindByName
	private long id;

	@CsvBindByName
	private String code;

	@CsvBindByName
	private String name;

	@CsvBindByName
	private String continent;

	@CsvBindByName
	private String wikipedia_link;

	@CsvBindByName
	private String keywords;
}
