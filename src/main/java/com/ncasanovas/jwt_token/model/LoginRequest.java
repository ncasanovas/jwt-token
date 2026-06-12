package com.ncasanovas.jwt_token.model;

public record LoginRequest(
    String email,
    String password) {

}
