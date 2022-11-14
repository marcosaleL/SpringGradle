package com.marcos.lazarte.evaluacion.data;

import com.marcos.lazarte.evaluacion.model.dto.RequestLoginDTO;
import com.marcos.lazarte.evaluacion.model.dto.RequestSignUpDTO;
import com.marcos.lazarte.evaluacion.model.dto.ResponseLoginDTO;
import com.marcos.lazarte.evaluacion.model.dto.ResponseSignUpDTO;
import com.marcos.lazarte.evaluacion.model.entities.UserEntity;

public final class ServiceTestData {

	private static RequestSignUpDTO requestSingUp = RequestSignUpDTO.builder()
			.name("marcos").email("marcos@github.com").password("a2asfGfdfdf4").build();
	
	private static RequestLoginDTO requestLogin = RequestLoginDTO.builder()
			.email("marcos@github.com").password("a2asfGfdfdf4").token("blablablablabla").build();
	
	private ServiceTestData() {
		
	}
	
	public static RequestSignUpDTO getRequestSignUp() {
        return requestSingUp;
    }

    public static UserEntity getUserEntity() {
        return new UserEntity(requestSingUp);
    }

    public static ResponseSignUpDTO getResponseSignUp() {
        return new ResponseSignUpDTO(getUserEntity());
    }

    public static RequestLoginDTO getRequestLogin() {
    	return requestLogin;
    }
    
    public static ResponseLoginDTO getResponseLogin() {
    	return new ResponseLoginDTO(getUserEntity());
    }
	
}
