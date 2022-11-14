package com.marcos.lazarte.evaluacion.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.marcos.lazarte.evaluacion.exceptions.BadRequestException;
import com.marcos.lazarte.evaluacion.exceptions.InternalServerErrorException;
import com.marcos.lazarte.evaluacion.model.dto.RequestLoginDTO;
import com.marcos.lazarte.evaluacion.model.dto.RequestSignUpDTO;
import com.marcos.lazarte.evaluacion.model.dto.ResponseLoginDTO;
import com.marcos.lazarte.evaluacion.model.dto.ResponseSignUpDTO;
import com.marcos.lazarte.evaluacion.model.entities.UserEntity;
import com.marcos.lazarte.evaluacion.repository.IUserRepository;
import com.marcos.lazarte.evaluacion.security.JwtProvider;
import com.marcos.lazarte.evaluacion.security.PasswordSecurity;
import com.marcos.lazarte.evaluacion.service.IUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

	private static final String DATA_TIME_FORMAT = "LLL dd, yyyy hh:mm:ss a";

	IUserRepository userRepository;

	JwtProvider jwtProvider;
	
	PasswordSecurity passwordSecurity;

	@Override
	public ResponseSignUpDTO signUp(RequestSignUpDTO requestSignUpDTO) {
		if (userRepository.existsByEmail(requestSignUpDTO.getEmail()))
			throw new InternalServerErrorException("Email already exist");
		if (!passwordSecurity.passwordValidation(requestSignUpDTO.getPassword()))
			throw new BadRequestException(
					"The password is invalid. No special characters were submitted, it must contain only one uppercase letter, two numbers, and must be between 8 and 12 characters");
		UserEntity userEntity = new UserEntity(requestSignUpDTO);
		userEntity.setPassword(passwordSecurity.encryptPassword(userEntity.getPassword()));
		UserEntity userInserted = userRepository.save(userEntity);
		ResponseSignUpDTO responseSignUp = new ResponseSignUpDTO(userInserted);
		responseSignUp.setToken(jwtProvider.createToken(requestSignUpDTO.getEmail()));
		return responseSignUp;

	}

	@Override
	public ResponseLoginDTO login(RequestLoginDTO requestLoginDTO) {
		if (!userRepository.existsByEmail(requestLoginDTO.getEmail()))
			throw new InternalServerErrorException("Email does not exist");
		UserEntity presentUser = userRepository.findByEmail(requestLoginDTO.getEmail());
		if (passwordSecurity.verifyPassword(requestLoginDTO.getPassword(), presentUser.getPassword())) {
			if (jwtProvider.validate(requestLoginDTO.getToken())) {
				presentUser.setLastLogin(
						LocalDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern(DATA_TIME_FORMAT)));
				UserEntity updatedUser = userRepository.save(presentUser);
				ResponseLoginDTO responseLoginDTO = new ResponseLoginDTO(updatedUser);
				responseLoginDTO.setPassword(requestLoginDTO.getPassword());
				responseLoginDTO.setToken(jwtProvider.createToken(responseLoginDTO.getEmail()));
				return responseLoginDTO;
			} else
				throw new BadRequestException("Token is incorrect");
		} else
			throw new BadRequestException("Password is incorrect");
	}

}
