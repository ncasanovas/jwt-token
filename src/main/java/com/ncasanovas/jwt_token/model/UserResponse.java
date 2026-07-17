package com.ncasanovas.jwt_token.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponse(
        @JsonProperty("name") String name,
        @JsonProperty("email") String email) {

}
