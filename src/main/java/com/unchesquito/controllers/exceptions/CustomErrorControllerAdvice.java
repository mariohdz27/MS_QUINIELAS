package com.unchesquito.controllers.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.unchesquito.controllers.exceptions.http.BadRequestException;

@ControllerAdvice
@RestControllerAdvice
public class CustomErrorControllerAdvice extends ResponseEntityExceptionHandler {
	
	

	@ExceptionHandler(BadRequestException.class)
	public final  ResponseEntity<ExceptionResponse> customBadRequestException(BadRequestException exception ){
		 ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),exception.getMessage(), exception.getErrors(),
			        HttpStatus.BAD_REQUEST.getReasonPhrase());
			    return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}