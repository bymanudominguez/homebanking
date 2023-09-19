package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

    /*@Autowired
    private PasswordEncoder passwordEncoder;*/

    public static void main(String[] args) {
        SpringApplication.run(HomebankingApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository) {
        return args -> {

            /*
            Client client1 = new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("mad123"));
            clientRepository.save(client1);

            Client client2 = new Client("Lucia", "Colombo", "lucolombo@gmail.com", passwordEncoder.encode("mad123"));
            clientRepository.save(client2);

            Loan loanMortgage = new Loan("Mortgage");
            loanMortgage.setMaxAmount(500.000);
            loanMortgage.setPayments(List.of(12, 24, 36, 48, 60));

            Loan loanPersonal = new Loan("Personal");
            loanPersonal.setMaxAmount(100.000);
            loanPersonal.setPayments(List.of(6, 12, 24));

            Loan car = new Loan("Car");
            car.setMaxAmount(300.000);
            car.setPayments(List.of(6, 12, 24, 36));

            loanRepository.save(loanMortgage);
            loanRepository.save(loanPersonal);
            loanRepository.save(car);

            ClientLoan clientLoan1 = new ClientLoan();
            clientLoan1.setLoan(loanMortgage);
            clientLoan1.setAmount(400000);
            clientLoan1.setPayments(60);
            clientLoan1.setClient(client1);

            ClientLoan clientLoan2 = new ClientLoan();
            clientLoan2.setLoan(loanPersonal);
            clientLoan2.setAmount(50000);
            clientLoan2.setPayments(12);
            clientLoan2.setClient(client1);

            ClientLoan clientLoan3 = new ClientLoan();
            clientLoan3.setLoan(loanPersonal);
            clientLoan3.setAmount(100000);
            clientLoan3.setPayments(24);
            clientLoan3.setClient(client2);

            ClientLoan clientLoan4 = new ClientLoan();
            clientLoan4.setLoan(car);
            clientLoan4.setAmount(200000);
            clientLoan4.setPayments(36);
            clientLoan4.setClient(client2);

            clientLoanRepository.save(clientLoan1);
            clientLoanRepository.save(clientLoan2);
            clientLoanRepository.save(clientLoan3);
            clientLoanRepository.save(clientLoan4);

            Card card1 = new Card(LocalDate.now(), CardType.DEBIT, CardColor.GOLD);
            card1.setThruDate(card1.getFromDate().plusYears(5));
            card1.setCvv(628);
            card1.setNumber("215644389");
            card1.setCardHolder(client1.getFirstName() + " " + client1.getLastName());
            client1.addCard(card1);
            cardRepository.save(card1);

            Card card2 = new Card(LocalDate.now(), CardType.CREDIT, CardColor.TITANIUM);
            card2.setThruDate(card2.getFromDate().plusYears(5));
            card2.setCvv(146);
            card2.setNumber("215441422");
            card2.setCardHolder(client1.getFirstName() + " " + client1.getLastName());
            client1.addCard(card2);
            cardRepository.save(card2);

            Card card3 = new Card(LocalDate.now(), CardType.CREDIT, CardColor.SILVER);
            card3.setThruDate(card3.getFromDate().plusYears(5));
            card3.setCvv(782);
            card3.setNumber("565441434");
            card3.setCardHolder(client2.getFirstName() + " " + client2.getLastName());
            client2.addCard(card3);
            cardRepository.save(card3);

            Account account1 = new Account("VIN001", LocalDateTime.now());
            account1.setBalance(5000.00);
            client1.addAccount(account1);
            accountRepository.save(account1);

            Transaction transaction1 = new Transaction(TransactionType.CREDIT, 50000.0, "Varios", LocalDateTime.now());

            Transaction transaction2 = new Transaction(TransactionType.DEBIT, (-10000.0), "Aporte de capital", LocalDateTime.now());

            account1.addTransaction(transaction1);
            account1.addTransaction(transaction2);
            transactionRepository.save(transaction1);
            transactionRepository.save(transaction2);

            Account account2 = new Account("VIN002", LocalDateTime.now());
            account2.setBalance(7500.00);
            account2.setCreationDate(account2.getCreationDate().plusDays(1));
            client1.addAccount(account2);
            accountRepository.save(account2);

            Transaction transaction3 = new Transaction(TransactionType.CREDIT, 34000.0, "Varios", LocalDateTime.now());

            Transaction transaction4 = new Transaction(TransactionType.DEBIT, (-90000.0), "Aporte de capital", LocalDateTime.now());

            account2.addTransaction(transaction3);
            account2.addTransaction(transaction4);
            transactionRepository.save(transaction3);
            transactionRepository.save(transaction4);

            Account account3 = new Account("VIN003", LocalDateTime.now());
            account3.setBalance(1000000.00);
            client2.addAccount(account3);
            accountRepository.save(account3);

            Transaction transaction5 = new Transaction(TransactionType.CREDIT, 10000.0, "Varios", LocalDateTime.now());

            Transaction transaction6 = new Transaction(TransactionType.DEBIT, (-30000.0), "Aporte de capital", LocalDateTime.now());

            account3.addTransaction(transaction5);
            account3.addTransaction(transaction6);
            transactionRepository.save(transaction5);
            transactionRepository.save(transaction6);

            Account account4 = new Account("VIN004", LocalDateTime.now());
            account4.setBalance(10500.00);
            client2.addAccount(account4);
            accountRepository.save(account4);

            Transaction transaction7 = new Transaction(TransactionType.CREDIT, 70000.0, "Varios", LocalDateTime.now());

            Transaction transaction8 = new Transaction(TransactionType.DEBIT, (-80000.0), "Compra Online", LocalDateTime.now());

            account4.addTransaction(transaction7);
            account4.addTransaction(transaction8);
            transactionRepository.save(transaction7);
            transactionRepository.save(transaction8);

            clientRepository.save(new Client("admin", "admin", "admin@mindhub.com", passwordEncoder.encode("admin")));
            */
        };
    }
}
