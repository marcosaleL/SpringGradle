package com.marcos.lazarte.evaluacion.model.dto;

import java.util.List;

import com.marcos.lazarte.evaluacion.model.entities.PhoneEntity;
import com.marcos.lazarte.evaluacion.model.entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSignUpDTO {

	private Long userId;
	private String name;
    private String email;
    private String password;
    private List<PhoneEntity> phones;
	private String created;
	private String lastLogin;
	private String token;
	private boolean isActive;
	
	public ResponseSignUpDTO(UserEntity user) {
		this.userId = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.phones = user.getPhones();
		this.created = user.getCreated();
		this.lastLogin = user.getLastLogin();
		this.isActive = true;
	}
	
}
