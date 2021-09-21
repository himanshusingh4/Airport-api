package com.accenture.test.airport.testdata;

import java.util.ArrayList;
import java.util.List;

import com.accenture.test.airport.dto.AirportDetailsDto;
import com.accenture.test.airport.dto.CountryAirportDetailsDto;
import com.accenture.test.airport.dto.TotalAirportsDto;
import com.accenture.test.airport.model.Airport;
import com.accenture.test.airport.model.Runway;

public class Data {

	public static List<TotalAirportsDto> getTotalAirportsDtoList() {

		TotalAirportsDto totalAirportsDto1 = TotalAirportsDto.builder().countryCode("NL").numberOfAirports(10).build();
		TotalAirportsDto totalAirportsDto2 = TotalAirportsDto.builder().countryCode("IN").numberOfAirports(8).build();
		TotalAirportsDto totalAirportsDto3 = TotalAirportsDto.builder().countryCode("US").numberOfAirports(25).build();
		TotalAirportsDto totalAirportsDto4 = TotalAirportsDto.builder().countryCode("NL").numberOfAirports(10).build();

		List<TotalAirportsDto> totalAirportsList = new ArrayList<>();

		totalAirportsList.add(totalAirportsDto1);
		totalAirportsList.add(totalAirportsDto2);
		totalAirportsList.add(totalAirportsDto3);
		totalAirportsList.add(totalAirportsDto4);

		return totalAirportsList;

	}

	public static List<Airport> getAirportList() {

		List<Airport> airportList = new ArrayList<>();

		Airport a1 = new Airport();
		a1.setIso_country("IN");
		a1.setId(123);
		a1.setName("CSM International airport");

		Airport a2 = new Airport();
		a2.setIso_country("NL");
		a2.setId(456);
		a2.setName("Schiphol airport");

		airportList.add(a1);
		airportList.add(a2);

		return airportList;
	}

	public static List<AirportDetailsDto> getRunwayDetailsForAirport() {

		List<Runway> runwayList1 = new ArrayList<>();
		List<Runway> runwayList2 = new ArrayList<>();

		Runway runway1 = new Runway();
		runway1.setAirport_ref(123);

		Runway runway2 = new Runway();
		runway2.setAirport_ref(123);

		Runway runway3 = new Runway();
		runway3.setAirport_ref(456);

		Runway runway4 = new Runway();
		runway4.setAirport_ref(456);

		runwayList1.add(runway1);
		runwayList1.add(runway2);

		runwayList2.add(runway3);
		runwayList2.add(runway4);

		List<AirportDetailsDto> airportDetailsDtoList = new ArrayList<>();

		airportDetailsDtoList.add(AirportDetailsDto.builder().airportId(123).runways(runwayList1).build());
		airportDetailsDtoList.add(AirportDetailsDto.builder().airportId(456).runways(runwayList2).build());

		return airportDetailsDtoList;

	}

	public static CountryAirportDetailsDto getCountryAirportDetailsDto() {

		return CountryAirportDetailsDto.builder().airports(getRunwayDetailsForAirport()).countryCode("IN").build();
	}
}
