package io.codefountain.spring.boot.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class SpringBootAuthorizationServerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAuthorizationServerAppApplication.class, args);
	}

}
