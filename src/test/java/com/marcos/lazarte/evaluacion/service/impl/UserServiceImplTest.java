package com.marcos.lazarte.evaluacion.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marcos.lazarte.evaluacion.data.ServiceTestData;
import com.marcos.lazarte.evaluacion.model.dto.ResponseLoginDTO;
import com.marcos.lazarte.evaluacion.model.dto.ResponseSignUpDTO;
import com.marcos.lazarte.evaluacion.model.entities.UserEntity;
import com.marcos.lazarte.evaluacion.repository.IUserRepository;
import com.marcos.lazarte.evaluacion.security.JwtProvider;
import com.marcos.lazarte.evaluacion.security.PasswordSecurity;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private IUserRepository userRepository;

	@Mock
	private JwtProvider jwtProvider;

	@DisplayName("Test para comprobar que se retorne bien la creación del usuario")
	@Test
	void signUpTestOk() {
		UserEntity userTest = ServiceTestData.getUserEntity();
		ResponseSignUpDTO expectedResponseSignUp = ServiceTestData.getResponseSignUp();

		when(userRepository.existsByEmail(anyString())).thenReturn(Boolean.TRUE);
		when(PasswordSecurity.passwordValidation(anyString())).thenReturn(Boolean.TRUE);
		when(userRepository.save(any(UserEntity.class))).thenReturn(userTest);

		ResponseSignUpDTO actualResponseSignUp = userService.signUp(ServiceTestData.getRequestSignUp());

		assertEquals(actualResponseSignUp, expectedResponseSignUp);
	}

	@DisplayName("Test para comprobar que se retorne bien el mensaje al momento del login")
	@Test
	void loginTestOk() {
		UserEntity userTest = ServiceTestData.getUserEntity();
		ResponseLoginDTO expectedResponseLogin = ServiceTestData.getResponseLogin();

		when(userRepository.save(any(UserEntity.class))).thenReturn(userTest);

		ResponseLoginDTO actualResponseLogin = userService.login(ServiceTestData.getRequestLogin());

		assertEquals(actualResponseLogin, expectedResponseLogin);
	}

}
