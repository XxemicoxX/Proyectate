package com.example.proyectate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.proyectate.feature.users.UserRepository;
import com.example.proyectate.feature.users.User;
import com.example.proyectate.util.RolSistema;

@SpringBootApplication
public class ProyectateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectateApplication.class, args);
	}

	@Bean //Pasamos el "PasswordEncoder encoder" cómo parámetro para que esté inicializado
	CommandLineRunner createDefaultUsers(UserRepository repository, PasswordEncoder encoder) {
		return args -> {
			if (repository.findByEmail("admin@mail.com").isEmpty()) {
				User user = new User();
				user.setName("admin");
				user.setEmail("admin@mail.com");
				user.setPassword(encoder.encode("123456"));
				user.setRol(RolSistema.ADMIN);
				repository.save(user);
			}
		};
	}
}
