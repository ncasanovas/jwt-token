package com.ncasanovas.jwt_token.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncasanovas.jwt_token.model.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

  List<Token> findAllValidIsFalseOrRevokedIsFalseByUserId(Long id);

  Optional<Token> findByToken(String token);

}
