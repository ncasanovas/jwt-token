package com.ncasanovas.jwt_token.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncasanovas.jwt_token.model.UserResponse;
import com.ncasanovas.jwt_token.repository.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

  private final UserRepository userRepository;

  @GetMapping
  public List<UserResponse> getUsers() {
    final var users = userRepository.findAll();
    return users.stream()
        .map(user -> new UserResponse(user.getName(), user.getEmail()))
        .toList();
  }

}
