package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner init (ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository){
		return args -> {
			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com");
			clientRepository.save(client1);

			Client client2 = new Client("Lucia", "Colombo", "lucolombo@gmail.com");
			clientRepository.save(client2);

			Loan loan1 = new Loan("Hipotecario");
			loan1.setMaxAmount(500.000);
			loan1.setPayments(List.of(12,24,36,48,60));

			Loan loan2 = new Loan("Personal");
			loan2.setMaxAmount(100.000);
			loan2.setPayments(List.of(6, 12, 24));

			Loan loan3 = new Loan("Automotriz");
			loan3.setMaxAmount(300.000);
			loan3.setPayments(List.of(6,12,24,36));

			loanRepository.save(loan1);
			loanRepository.save(loan2);
			loanRepository.save(loan3);

			ClientLoan clientLoan1 = new ClientLoan();
			clientLoan1.setLoan(loan1);
			clientLoan1.setAmount(400000);
			clientLoan1.setPayments(60);
			clientLoan1.setClient(client1);

			ClientLoan clientLoan2 = new ClientLoan();
			clientLoan2.setLoan(loan2);
			clientLoan2.setAmount(50000);
			clientLoan2.setPayments(12);
			clientLoan2.setClient(client1);

			ClientLoan clientLoan3 = new ClientLoan();
			clientLoan3.setLoan(loan2);
			clientLoan3.setAmount(100000);
			clientLoan3.setPayments(24);
			clientLoan3.setClient(client2);

			ClientLoan clientLoan4 = new ClientLoan();
			clientLoan4.setLoan(loan3);
			clientLoan4.setAmount(200000);
			clientLoan4.setPayments(36);
			clientLoan4.setClient(client2);

			clientLoanRepository.save(clientLoan1);
			clientLoanRepository.save(clientLoan2);
			clientLoanRepository.save(clientLoan3);
			clientLoanRepository.save(clientLoan4);

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
