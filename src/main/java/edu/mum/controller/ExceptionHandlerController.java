package edu.mum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.mum.domain.dto.DomainError;
import edu.mum.domain.dto.DomainErrors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionHandlerController {

	@Autowired
	MessageSourceAccessor messageAccessor;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public DomainErrors handleParameterException(MethodArgumentNotValidException  exception) {
		DomainErrors errors = new DomainErrors();
		errors.setErrorType("invalidException");
		exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
			errors.addError(new DomainError(messageAccessor.getMessage(fieldError)));
		});
		return errors;
	}
}
