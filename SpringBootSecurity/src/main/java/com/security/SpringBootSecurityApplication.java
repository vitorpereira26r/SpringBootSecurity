package com.security;

import com.security.model.ApplicationUser;
import com.security.model.Role;
import com.security.repository.RoleRepository;
import com.security.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootSecurityApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder encoder){
		return args -> {
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;

			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			ApplicationUser admin = new ApplicationUser(1, "admin", encoder.encode("password"), roles);

			userRepository.save(admin);
		};
	}
}
