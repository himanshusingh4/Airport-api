package com.accenture.test.airport.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Exception class in case of bad input request given in API
 *
 */
@Getter
@AllArgsConstructor
public class BadRequestException extends Exception {

	private static final long serialVersionUID = 4297447388675175252L;

	private final ErrorResponse errorResponse;
	
}
