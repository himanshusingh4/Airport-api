package com.accenture.test.airport.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ElementNotFoundException extends Exception {

	private static final long serialVersionUID = -2041908128726602536L;

	private final ErrorResponse errorResponse;
	
}
