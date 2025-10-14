package com.example.proyectate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.proyectate.feature.usuarios.Usuarios;
import com.example.proyectate.util.RolSistema;
import com.example.proyectate.feature.usuarios.UsuarioRepository;

@SpringBootApplication
public class ProyectateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectateApplication.class, args);
	}

	@Bean //Pasamos el "PasswordEncoder encoder" cómo parámetro para que esté inicializado
	CommandLineRunner createDefaultUsers(UsuarioRepository repository, PasswordEncoder encoder) {
		return args -> {
			if (repository.findByEmail("admin@mail.com").isEmpty()) {
				Usuarios user = new Usuarios();
				user.setNombre("admin");
				user.setEmail("admin@mail.com");
				user.setContrasena(encoder.encode("123456"));
				user.setRol(RolSistema.ADMINISTRADOR);
				repository.save(user);
			}
		};
	}
}
