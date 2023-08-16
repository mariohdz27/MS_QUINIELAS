package com.unchesquito.controllers.exceptions;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResponse {

	private Date timestamp;
	private String message;
	private List<String> errors;
	private String  status;
	
}
