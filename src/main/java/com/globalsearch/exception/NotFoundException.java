package com.globalsearch.exception;

public class NotFoundException extends RuntimeException {
	public NotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
