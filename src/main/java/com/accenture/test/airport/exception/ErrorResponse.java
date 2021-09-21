package com.accenture.test.airport.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Error response class which will be return in output in case anything goes wrong in the API.
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
	
	private String code;
	
	private String message;

}
