package com.marcos.lazarte.evaluacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcos.lazarte.evaluacion.model.entities.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);

	boolean existsByEmail(String email);
}
