package com.bideeparts.gallery.artgallery.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bideeparts.gallery.artgallery.model.to.ErrorTO;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorTO handleCustomException(Exception e) {
		return new ErrorTO(500, e.getMessage());
	}
}
