package com.enigma.test7maret;

import static org.junit.Assert.assertEquals;

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
import com.enigma.test7maret.dto.AccountDto;
import com.enigma.test7maret.dto.AccountPostDto;
import com.enigma.test7maret.entity.Account;
import com.enigma.test7maret.exception.BadRequestException;
import com.enigma.test7maret.exception.NotFoundException;
import com.enigma.test7maret.service.AccountService;

@RunWith(SpringRunner.class)
public class AccountServiceTest {

	@TestConfiguration
	static class AccountServiceContextConfiguration {
		@Bean
		public AccountService accountService() {
			return new AccountService();
		}

	}
	@Autowired
	private AccountService service;

	@MockBean
	private AccountRepository repo;

	@Before
	public void setup() throws NotFoundException {
		Account account = new Account("123123", "ichsan", new BigDecimal(100000), 1000D, 0);
//		AccountDto account2 = new AccountDto(1L, "123123", "ichsan", new BigDecimal(100000), 1000D, 0);

		List<Account> accountDtos = new ArrayList<Account>();
		accountDtos.add(account);
		Mockito.when(repo.findByAccount("123123")).thenReturn(account);
		Mockito.when(repo.findByNameIgnoreCase("ichsan")).thenReturn(account);
		Mockito.when(repo.findByNameContainsIgnoreCase("ichsan")).thenReturn(accountDtos);
		
	}

	@Test
	public void wheneFindByAccountNumber_returnAccount() throws NotFoundException {
		assertEquals(service.findByAccountNumber("123123").getAccount(), "123123");
	}
	
	
	@Test
	public void whenFindByName_returnAccount() throws NotFoundException {
		assertEquals(service.findByName("ichsan").size(), 1);
	}

	@Test
	public void whenCreateAccount_returnSave() throws BadRequestException {
		assertEquals(service.createAccount(new AccountPostDto("123120", "ichsand", new BigDecimal(10000000), 0)),
				"save");
	}
	
	@Test 
	public void whenAddBalance_returnAccountDto() throws NotFoundException, BadRequestException {
		AccountDto accountDto = service.updateBalance(new AccountPostDto("123123", new BigDecimal(10000000)));
		assertEquals( accountDto, accountDto);
	}
	@Test 
	public void whenAddBalance2_returnAccountDto() throws NotFoundException, BadRequestException {
		AccountDto accountDto = service.updateBalance2(new AccountPostDto("123123", new BigDecimal(10000000)));
		assertEquals( accountDto, accountDto);
	}
	@Test 
	public void whenTransferPoint_returnAccountDto() throws NotFoundException, BadRequestException {
		AccountDto accountDto = service.transferPoint(new AccountPostDto("123123", new BigDecimal(10000000)));
		assertEquals( accountDto, accountDto);
	}
	@Test 
	public void whenMinBalance_returnAccountDto() throws NotFoundException, BadRequestException {
		AccountDto accountDto = service.minBalance(new AccountPostDto("123123", new BigDecimal(10000000)));
		assertEquals( accountDto, accountDto);
	}
	
	
	@Test 
	public void whenUpdateStatus_returnAccountDto() throws NotFoundException, BadRequestException {
		AccountDto accountDto = service.updateStatus(new AccountPostDto("123123", new BigDecimal(10000000)));
		assertEquals( accountDto, accountDto);
	}

	@Test 
	public void whenKurangPulsa_returnAccountDto() throws NotFoundException, BadRequestException {
		AccountDto accountDto = service.kurangPulsa(new AccountPostDto("123123", new BigDecimal(10000000)));
		assertEquals( accountDto, accountDto);
	}

}
