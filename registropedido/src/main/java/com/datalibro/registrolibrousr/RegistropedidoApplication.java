package com.datalibro.registrolibrousr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.datalibro.registrolibrousr.model.Role;
import com.datalibro.registrolibrousr.model.User;
import com.datalibro.registrolibrousr.repository.UserRepository;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class RegistropedidoApplication {
	public static void main(String[] args) {
		SpringApplication.run(RegistropedidoApplication.class, args);
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
