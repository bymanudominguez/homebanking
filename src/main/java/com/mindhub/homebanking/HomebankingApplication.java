package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.Id;
import java.time.LocalDate;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner init (ClientRepository clientRepository, AccountRepository accountRepository){
		return args -> {
			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com");
			clientRepository.save(client1);

			Client client2 = new Client("Lucia", "Colombo", "lucolombo@gmail.com");
			clientRepository.save(client2);

			Account account1 = new Account();
			account1.setNumber("VIN001");
			account1.getCreationDate();
			account1.setBalance(5000.00);
			client1.addAccount(account1);
			accountRepository.save(account1);

			Account account2 = new Account();
			account2.setNumber("VIN002");
			account2.getCreationDate();
			account2.setBalance(7500.00);
			client1.addAccount(account2);
			accountRepository.save(account2);

			Account account3 = new Account();
			account3.setNumber("VIN003");
			account3.getCreationDate();
			account3.setBalance(1000000.00);
			client2.addAccount(account3);
			accountRepository.save(account3);

			Account account4 = new Account();
			account4.setNumber("VIN004");
			account4.getCreationDate();
			account4.setBalance(10500.00);
			client2.addAccount(account4);
			accountRepository.save(account4);

			clientRepository.save(new Client("Manuel", "Domínguez", "bymanudominguez@gmail.com"));
			clientRepository.save(new Client("Frodo", "Baggins", "myprecious@lor.com"));

		};
	}
}
