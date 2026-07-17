package com.ncasanovas.jwt_token.model;

public record RegisterRequest(
                String email,
                String password,
                String name) {

}
