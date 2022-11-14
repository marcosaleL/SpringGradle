package com.marcos.lazarte.evaluacion.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLoginDTO {

	@NotEmpty(message = "Email must not be empty")
	@Email(message = "Email it has to be valid")
	private String email;
	
	@NotEmpty(message = "Password must not be empty")
	private String password;
	
	private String token;
	
}
