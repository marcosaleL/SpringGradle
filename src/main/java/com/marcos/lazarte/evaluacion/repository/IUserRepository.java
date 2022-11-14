package com.marcos.lazarte.evaluacion.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcos.lazarte.evaluacion.model.entities.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, UUID> {

	Optional<UserEntity> findByEmail(String email);

	boolean existsByEmail(String email);
}
