package com.accenture.airport.exception;

/**
 * Exception class for technical error in API
 *
 */
public class TechnicalException extends RuntimeException {

	private static final long serialVersionUID = 6363416694700640780L;

	public TechnicalException(String message) {
		super(message);
	}

}
