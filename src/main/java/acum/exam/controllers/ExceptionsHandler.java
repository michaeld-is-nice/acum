package acum.exam.controllers;

import acum.exam.ResourceNotFoundExceptionException;
import acum.exam.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(value = ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody String handleValidationException(ValidationException ex) {

		return ex.getMessage();
	}

	@ExceptionHandler(value = ResourceNotFoundExceptionException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleResourceNotFoundExceptionException() {

	}

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleException(Exception ex) {

		log.error("", ex);
	}
}
