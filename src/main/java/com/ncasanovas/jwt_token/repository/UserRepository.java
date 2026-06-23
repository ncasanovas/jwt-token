package com.ncasanovas.jwt_token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncasanovas.jwt_token.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  java.util.Optional<User> findByEmail(String email);
}
