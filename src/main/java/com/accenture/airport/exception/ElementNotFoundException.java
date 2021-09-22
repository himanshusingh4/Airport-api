package com.accenture.airport.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Exception class in case the required element is not found.
 *
 */
@Getter
@AllArgsConstructor
public class ElementNotFoundException extends Exception {

	private static final long serialVersionUID = -2041908128726602536L;

	private final ErrorResponse errorResponse;
	
}
