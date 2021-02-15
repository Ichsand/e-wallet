package com.enigma.test7maret;
import static org.junit.Assert.assertEquals;

//import java.util.Date;
//import java.text.SimpleDateFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.enigma.test7maret.Repository.AccountRepository;
import com.enigma.test7maret.Repository.TransactionRepository;
import com.enigma.test7maret.entity.Account;
import com.enigma.test7maret.entity.Transaction;
import com.enigma.test7maret.entity.TransactionPostDto;
import com.enigma.test7maret.exception.BadRequestException;
import com.enigma.test7maret.exception.NotFoundException;
import com.enigma.test7maret.service.AccountService;
import com.enigma.test7maret.service.TransactionService;

@RunWith(SpringRunner.class)
public class TransactionServiceTests {
	
	@TestConfiguration
	static class TransactionServiceContextConfiguration{
		@Bean
		public TransactionService transactionService() {
			return new TransactionService();
		}
		@Bean
		public AccountService accountService() {
			return new AccountService();
		}
	}
	
	@Autowired
	private TransactionService service;
	@MockBean
	private TransactionRepository repo;
	@MockBean
	private AccountRepository repoAccount;
	
	@Before
	public void setup() {

//		java.util.Date udate = new Date();
//		SimpleDateFormat format = new SimpleDateFormat("2020-01-01");
//		java.sql.Date date = new java.sql.Date(udate.getTime()); 
		Account to = new Account("123123", "ipul", new BigDecimal(100000), 1000D, 0);
		Account from = new Account("123124", "ipul", new BigDecimal(100000), 1000D, 0);
		Transaction transaction = new Transaction();
		transaction.setId(1L);
		transaction.setTransactionDate(java.sql.Date.valueOf("2020-01-01"));
		transaction.setAmount(new BigDecimal(10000000));
		transaction.setType("test");
		transaction.setDescription("test");
		transaction.setFrom(from);
		transaction.setTo(to);

		List<Transaction> dtos = new ArrayList<Transaction>();
		dtos.add(transaction);	
		Mockito.when(repo.save(transaction)).thenReturn(transaction);
		Mockito.when(repoAccount.findByAccount("123123")).thenReturn(to);
		Mockito.when(repoAccount.save(to)).thenReturn(to);
		Mockito.when(repo.findAllByFromAndDateSQL(1L, java.sql.Date.valueOf("2020-01-01"))).thenReturn(dtos);
		
	}
	
	@Test
	public void whenSearchTransactionFromAccount_thanReturnListTransaction() throws NotFoundException {
//		java.util.Date udate = new Date();
//		SimpleDateFormat formats = new SimpleDateFormat("ddmmyyyy");
//		String date = formats.format(udate);
		assertEquals(service.searchTransactionFromAccount("123123","01012020").size(), 0);
	}

	@Test
	public void wheneaddBalance() throws NotFoundException, BadRequestException {
		TransactionPostDto dto = new TransactionPostDto("123123",new BigDecimal(10000000));
		assertEquals(service.addBalance(dto),"save");
	}
	
	@Test
	public void whentransfer() throws NotFoundException, BadRequestException {
		TransactionPostDto dto = new TransactionPostDto("123123", "123123", new BigDecimal(10000), "transfer", "");
		assertEquals(service.transfer(dto).getAmount(), new BigDecimal(10000));
	}
	
	@Test
	public void transferPoint() throws NotFoundException, BadRequestException {
		TransactionPostDto dto = new TransactionPostDto("123123", "123123", new BigDecimal(10000), "point to balance", "");
		assertEquals(service.transferPoint(dto).getDescription(),"point to balance");

	}
	@Test
	public void buyPulsa() {
		
	}
	
}
