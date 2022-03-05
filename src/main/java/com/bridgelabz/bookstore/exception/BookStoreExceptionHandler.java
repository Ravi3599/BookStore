package com.bridgelabz.bookstore.exception;

import java.net.BindException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.bridgelabz.bookstore.dto.ResponseDTO;

//Ability to handle exception 
@ControllerAdvice
public class BookStoreExceptionHandler  {
	
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<ResponseDTO> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
			
				List<ObjectError> errorList=exception.getBindingResult().getAllErrors();
				List<String> errMesg = errorList.stream().map(objErr->objErr.getDefaultMessage()).collect(Collectors.toList());
				
				ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST requests",errMesg);
				return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
		}
		//Ability to handler User defined BookStoreException
		@ExceptionHandler(BookStoreException.class)
		public ResponseEntity<ResponseDTO> handleEmployeeNotFound(BookStoreException exception) {
			ResponseDTO response = new ResponseDTO("Invalid input", exception.getMessage());
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.BAD_REQUEST);
		}
	    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
	    	ResponseDTO response = new ResponseDTO("Please change the http method type", ex.getMessage());
			return new ResponseEntity<Object>(response, HttpStatus.METHOD_NOT_ALLOWED);
	    }
	    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
	    public ResponseEntity<ResponseDTO> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
	    	ResponseDTO response = new ResponseDTO("Please enter correct argument type for given http method...!", ex.getMessage());
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.BAD_REQUEST);
	    }
	    @ExceptionHandler(NoHandlerFoundException.class)
	    @ResponseStatus(value=HttpStatus.NOT_FOUND)
	    public ResponseEntity<Object> handleNotFoundException(NoHandlerFoundException ex) {
	    	ResponseDTO response = new ResponseDTO("Please enter correct API URL path...!", ex.getMessage());
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
	    }
	    @ExceptionHandler(BindException.class)
	    public ResponseEntity<ResponseDTO> handleBindException(BindException ex) {
	    	ResponseDTO response = new ResponseDTO("Current Port is being used by other application.You can stop other application or"
	    											+ "You can add new property 'server.port' with any other port", ex.getMessage());
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.SERVICE_UNAVAILABLE);
	    }
}

