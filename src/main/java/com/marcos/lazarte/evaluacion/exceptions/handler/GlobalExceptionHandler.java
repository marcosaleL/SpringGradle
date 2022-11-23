package com.marcos.lazarte.evaluacion.exceptions.handler;

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

@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<ErrorDetailDTO> handleInteralServerError(
			InternalServerErrorException internalServerErrorException) {
		ErrorDetailDTO errorDetail = new ErrorDetailDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				internalServerErrorException.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetail);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorDetailDTO> handleBadRequest(BadRequestException badRequestException) {
		ErrorDetailDTO errorDetail = new ErrorDetailDTO(HttpStatus.BAD_REQUEST.value(),
				badRequestException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetail);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetailDTO errorDetail = new ErrorDetailDTO(HttpStatus.BAD_REQUEST.value(),
				ex.getFieldError().getDefaultMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetail);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetailDTO> handleExceptions(Exception ex) {
		ErrorDetailDTO genericError = ErrorDetailDTO.createGenericError();
		return new ResponseEntity<>(genericError, HttpStatus.BAD_REQUEST);
	}

}
