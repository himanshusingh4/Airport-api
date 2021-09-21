package com.accenture.test.airport.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.accenture.test.airport.constant.ErrorCodes;
import com.accenture.test.airport.exception.BadRequestException;
import com.accenture.test.airport.exception.ElementNotFoundException;
import com.accenture.test.airport.exception.ErrorResponse;
import com.accenture.test.airport.exception.TechnicalException;

/**
 * Global exception handler class for Airport API
 *
 */
@ControllerAdvice
public class AirportAPIExceptionHandler {

	/**
	 * Exception handler method for ElementNotFoundException
	 * 
	 * @param ex elementNotFoundException
	 * @return Response entity with Error response for given exception
	 */
	@ExceptionHandler(ElementNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleElementNotFoundException(ElementNotFoundException ex) {

		return new ResponseEntity<>(ex.getErrorResponse(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Exception handler method for BadRequestException
	 * 
	 * @param ex badRequestException
	 * @return Response entity with Error response for given exception
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {

		return new ResponseEntity<>(ex.getErrorResponse(), HttpStatus.BAD_REQUEST);

	}

	/**
	 * Exception handler method for all other Exceptions
	 * 
	 * @param ex exception occurred
	 * @return Response entity with Error response for given exception
	 */
	@ExceptionHandler({ TechnicalException.class, Exception.class })
	public ResponseEntity<ErrorResponse> handleBadRequestException(Exception ex) {

		ErrorResponse error = new ErrorResponse(ErrorCodes.INTERNAL_SERVER_ERROR.name(),
				ErrorCodes.INTERNAL_SERVER_ERROR.getErrorMessage());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
