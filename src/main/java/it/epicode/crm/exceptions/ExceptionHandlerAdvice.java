package it.epicode.crm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ElementAlreadyPresentException.class)
	public ResponseEntity entityAlreadyFound(ElementAlreadyPresentException elp) {
		return new ResponseEntity(elp.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ElementNotFoundException.class)
	public ResponseEntity entityNotFound(ElementNotFoundException errore) {
		return new ResponseEntity(errore.getMessage(), HttpStatus.NOT_FOUND);
	}


}

