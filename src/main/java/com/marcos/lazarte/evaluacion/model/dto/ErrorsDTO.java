package com.marcos.lazarte.evaluacion.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ErrorsDTO {
	
	private List<ErrorDetailDTO> errors;
	
	public void addException(ErrorDetailDTO errorDetail) {
		errors.add(errorDetail);
	}

}
