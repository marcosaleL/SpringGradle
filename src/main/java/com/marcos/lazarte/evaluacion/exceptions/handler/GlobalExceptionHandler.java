package com.marcos.lazarte.evaluacion.exceptions.handler;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.marcos.lazarte.evaluacion.exceptions.BadRequestException;
import com.marcos.lazarte.evaluacion.exceptions.InternalServerErrorException;
import com.marcos.lazarte.evaluacion.model.dto.ErrorDetailDTO;
import com.marcos.lazarte.evaluacion.model.dto.ErrorsDTO;

@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<ErrorsDTO> handleInteralServerError(
			InternalServerErrorException internalServerErrorException) {
		ErrorDetailDTO errorDetail = new ErrorDetailDTO(
				HttpStatus.INTERNAL_SERVER_ERROR.value(), internalServerErrorException.getMessage());
		ErrorsDTO errors = new ErrorsDTO(new ArrayList<>());
		errors.addException(errorDetail);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorsDTO> handleBadRequest(BadRequestException badRequestException) {
		ErrorDetailDTO errorDetail = new ErrorDetailDTO(HttpStatus.BAD_REQUEST.value(), badRequestException.getMessage());
		ErrorsDTO errors = new ErrorsDTO(new ArrayList<>());
		errors.addException(errorDetail);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(errors);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetailDTO errorDetail = new ErrorDetailDTO(HttpStatus.BAD_REQUEST.value(), ex.getFieldError().getDefaultMessage());
		ErrorsDTO errors = new ErrorsDTO(new ArrayList<>());
		errors.addException(errorDetail);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(errors);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorsDTO> handleExceptions(Exception ex) {
		ErrorDetailDTO genericError = ErrorDetailDTO.createGenericError();
		ErrorsDTO errors = new ErrorsDTO(new ArrayList<>());
		errors.addException(genericError);
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	
}
