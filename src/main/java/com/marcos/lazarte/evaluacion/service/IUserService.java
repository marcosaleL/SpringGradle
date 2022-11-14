package com.marcos.lazarte.evaluacion.service;

import com.marcos.lazarte.evaluacion.model.dto.RequestLoginDTO;
import com.marcos.lazarte.evaluacion.model.dto.RequestSignUpDTO;
import com.marcos.lazarte.evaluacion.model.dto.ResponseLoginDTO;
import com.marcos.lazarte.evaluacion.model.dto.ResponseSignUpDTO;

public interface IUserService {
	
	public ResponseSignUpDTO signUp(RequestSignUpDTO requestSignUpDTO);
	
	public ResponseLoginDTO login(RequestLoginDTO requestLoginDTO);

}
