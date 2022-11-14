package com.marcos.lazarte.evaluacion.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "phone")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "number")
	private Long number;
	
	@Column(name = "city_code")
    private int cityCode;
	
	@Column(name = "name")
    private String countryCode;
	
}
