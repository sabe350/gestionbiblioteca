package com.datalibro.registrousuario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.datalibro.registrousuario.repository.UserRepository;
import com.datalibro.registrousuario.model.Role;
import com.datalibro.registrousuario.model.User;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class RegistrousuarioApplication {
	public static void main(String[] args) {
		SpringApplication.run(RegistrousuarioApplication.class, args);
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
