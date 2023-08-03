package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner init (ClientRepository clientRepository){
		return args -> {
			clientRepository.save(new Client("Melba", "Morel", "melba@mindhub.com"));
			clientRepository.save(new Client("Manuel", "Domínguez", "bymanudominguez@gmail.com"));
			clientRepository.save(new Client("Frodo", "Baggins", "myprecious@gmail.com"));
		};
	}
}
