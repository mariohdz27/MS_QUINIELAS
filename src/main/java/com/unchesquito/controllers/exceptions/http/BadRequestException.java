package com.unchesquito.controllers.exceptions.http;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class BadRequestException extends RuntimeException {

	
	private List<String>  errors;
	
	private static final long serialVersionUID = 1L;
	
	public BadRequestException(String message) {
	    super(message);
	  }

	public BadRequestException(String message, List<String> errors) {
	    super(message);
	    this.errors = errors;
	  }

}
