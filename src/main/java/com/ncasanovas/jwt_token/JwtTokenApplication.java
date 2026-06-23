package com.ncasanovas.jwt_token;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ncasanovas.jwt_token.config.DotenvConfig;

@SpringBootApplication
public class JwtTokenApplication {

	public static void main(String[] args) {
	    SpringApplication app = new SpringApplication(JwtTokenApplication.class);
        app.addInitializers(new DotenvConfig()); 
        app.run(args);
	}

}
