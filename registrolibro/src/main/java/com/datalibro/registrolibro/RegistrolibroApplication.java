package com.datalibro.registrolibro;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.datalibro.registrolibro.model.Role;
import com.datalibro.registrolibro.model.User;
import com.datalibro.registrolibro.repository.UserRepository;


@SpringBootApplication
@EnableScheduling
public class RegistrolibroApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrolibroApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(UserRepository repo, PasswordEncoder encoder){
		return args ->{
			if (repo.findByUsername("admin").isEmpty()){
				User user = new User();
				user.setUsername("admin");
				user.setPassword(encoder.encode("1234"));
				user.setRole(Role.ROLE_ADMIN);
				repo.save(user);
			}
		};
	}
}
