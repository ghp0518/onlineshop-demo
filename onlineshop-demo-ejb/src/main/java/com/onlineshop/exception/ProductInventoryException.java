package com.onlineshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Inventory validation failed" )
public class ProductInventoryException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ProductInventoryException() {
	}

	public ProductInventoryException(String message) {
		super(message);
	}

	public ProductInventoryException(Throwable cause) {
		super(cause);
	}

	public ProductInventoryException(String message, Throwable cause) {
		super(message, cause);
	}
}
