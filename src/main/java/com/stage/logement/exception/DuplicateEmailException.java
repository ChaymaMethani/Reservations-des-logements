package com.stage.logement.exception;

public class DuplicateEmailException extends Exception {

	private static final long serialVersionUID = 1L;

	public DuplicateEmailException(String message) {
		super(message);
	}

}