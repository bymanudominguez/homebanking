package com.mindhub.homebanking;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner init (ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository){
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

			Transaction transaction1 = new Transaction(TransactionType.CREDIT, 50000.0, "Varios", LocalDateTime.now());

			Transaction transaction2 = new Transaction(TransactionType.DEBIT, (-10000.0), "Aporte de capital", LocalDateTime.now());

			account1.addTransaction(transaction1);
			account1.addTransaction(transaction2);
			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);

			Account account2 = new Account();

			account2.setNumber("VIN002");
			account2.getCreationDate();
			account2.setBalance(7500.00);
			client1.addAccount(account2);
			accountRepository.save(account2);

			Transaction transaction3 = new Transaction(TransactionType.CREDIT, 34000.0, "Varios", LocalDateTime.now());

			Transaction transaction4 = new Transaction(TransactionType.DEBIT, (-90000.0), "Aporte de capital", LocalDateTime.now());

			account2.addTransaction(transaction3);
			account2.addTransaction(transaction4);
			transactionRepository.save(transaction3);
			transactionRepository.save(transaction4);

			Account account3 = new Account();

			account3.setNumber("VIN003");
			account3.getCreationDate();
			account3.setBalance(1000000.00);
			client2.addAccount(account3);
			accountRepository.save(account3);

			Transaction transaction5 = new Transaction(TransactionType.CREDIT, 10000.0, "Varios", LocalDateTime.now());

			Transaction transaction6 = new Transaction(TransactionType.DEBIT, (-30000.0), "Aporte de capital", LocalDateTime.now());

			account3.addTransaction(transaction5);
			account3.addTransaction(transaction6);
			transactionRepository.save(transaction5);
			transactionRepository.save(transaction6);

			Account account4 = new Account();

			account4.setNumber("VIN004");
			account4.getCreationDate();
			account4.setBalance(10500.00);
			client2.addAccount(account4);
			accountRepository.save(account4);

			Transaction transaction7 = new Transaction(TransactionType.CREDIT, 70000.0, "Varios", LocalDateTime.now());

			Transaction transaction8 = new Transaction(TransactionType.DEBIT, (-80000.0), "Aporte de capital", LocalDateTime.now());

			account4.addTransaction(transaction7);
			account4.addTransaction(transaction8);
			transactionRepository.save(transaction7);
			transactionRepository.save(transaction8);

			clientRepository.save(new Client("Frodo", "Baggins", "myprecious@lor.com"));

		};
	}
}
