package org.wallet.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(CustomerIdNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleCustomerIdNotFoundException(
			CustomerIdNotFoundException ex,
			WebRequest request){
		ErrorDetails details=new ErrorDetails();
		details.setTimestamp(LocalDateTime.now().toString());
		details.setMessage(ex.getMessage());
		details.setDescription(request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleException(
			Exception ex,
			WebRequest request){
		ErrorDetails details=new ErrorDetails();
		details.setTimestamp(LocalDateTime.now().toString());
		details.setMessage(ex.getMessage());
		details.setDescription(request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.NOT_FOUND);
		
	}
}
