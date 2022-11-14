package com.marcos.lazarte.evaluacion.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordSecurity {
	
	private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?!.*[!¡@#&()–[{}]:;',?¿/*~$^+=<>])(?=\\S+$).{8,12}$";
	
	private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
	
	private PasswordSecurity() {
		
	}
	
	public String encryptPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public boolean verifyPassword(String encryptedPassword, String password) {
		BCryptPasswordEncoder crypto = new BCryptPasswordEncoder();
		return crypto.matches(encryptedPassword, password);
	}
	
	public boolean passwordValidation(String password) {
		Matcher matcher = pattern.matcher(password);
        return matcher.matches();
	}
	
}
