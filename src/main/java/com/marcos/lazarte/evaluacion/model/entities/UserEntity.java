package com.marcos.lazarte.evaluacion.model.entities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.marcos.lazarte.evaluacion.model.dto.RequestSignUpDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

	private static final String DATA_TIME_FORMAT = "LLL dd, yyyy hh:mm:ss a";

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "created")
	private String created;

	@Column(name = "lastLogin")
	private String lastLogin;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "users_id")
	private List<PhoneEntity> phones;

	public UserEntity(RequestSignUpDTO signUp) {
		this.name = signUp.getName();
		this.email = signUp.getEmail();
		this.password = signUp.getPassword();
		this.created = LocalDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern(DATA_TIME_FORMAT));
		this.phones = signUp.getPhones();
		this.lastLogin = null;
	}
}
