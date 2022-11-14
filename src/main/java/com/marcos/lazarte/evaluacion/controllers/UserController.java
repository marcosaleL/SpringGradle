package com.marcos.lazarte.evaluacion.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.lazarte.evaluacion.model.dto.RequestLoginDTO;
import com.marcos.lazarte.evaluacion.model.dto.RequestSignUpDTO;
import com.marcos.lazarte.evaluacion.model.dto.ResponseLoginDTO;
import com.marcos.lazarte.evaluacion.model.dto.ResponseSignUpDTO;
import com.marcos.lazarte.evaluacion.service.IUserService;

@RestController
@RequestMapping("api/v1")
public class UserController {

	@Autowired
	private IUserService userService;

	@PostMapping("/sign_up")
	public ResponseEntity<ResponseSignUpDTO> signUp(@Valid @RequestBody RequestSignUpDTO signUp) {
		return new ResponseEntity<>(userService.signUp(signUp), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseLoginDTO> login(@Valid @RequestBody RequestLoginDTO requestLoginDTO){
		return new ResponseEntity<>(userService.login(requestLoginDTO), HttpStatus.OK);
	}

}
