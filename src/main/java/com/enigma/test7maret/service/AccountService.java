package com.enigma.test7maret.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.test7maret.Repository.AccountRepository;
import com.enigma.test7maret.dto.AccountDto;
import com.enigma.test7maret.dto.AccountPostDto;
import com.enigma.test7maret.entity.Account;
import com.enigma.test7maret.exception.BadRequestException;
import com.enigma.test7maret.exception.NotFoundException;

@Service
public class AccountService {
	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	AccountRepository repo;

	public AccountDto findByAccountNumber(String accountNumber) throws NotFoundException {
		Account account = repo.findByAccount(accountNumber);
		if (account != null) {
			ModelMapper mapper = new ModelMapper();
			AccountDto accountDto = mapper.map(account, AccountDto.class);
			return accountDto;
		} else {
			logger.error("Account " + accountNumber + " is Not Found!");
			throw new NotFoundException("Account " + accountNumber + " is Not Found!");
		}
	}

	public List<AccountDto> findByName(String name) throws NotFoundException {
		Account account = repo.findByNameIgnoreCase(name);
		List<AccountDto> list = new ArrayList<AccountDto>();
		if (account != null) {
			repo.findByNameContainsIgnoreCase(name).forEach(acc -> {
				ModelMapper mapper = new ModelMapper();
				AccountDto accountDto = mapper.map(account, AccountDto.class);
				list.add(accountDto);
			});
			return list;
		} else {
			logger.error("Name " + name + " is Not Found!");
			throw new NotFoundException("Name " + name + " is Not Found!");
		}
	}

	public void validateRegexAccountNumber(AccountPostDto accpost) throws BadRequestException {
		String regex = "[0-9]{6}";
		if (accpost.getAccount().matches(regex)) {
			logger.info("succes regex");
		} else {
			logger.warn("Account Mandatory");
			throw new BadRequestException("Account Mandatory");
		}
	}

	public void validateNameLength(AccountPostDto accpost) throws BadRequestException {
		if (accpost.getName().length() >= 5) {
			logger.info("succes validate");
		} else {
			logger.warn("Name Mandatory");
			throw new BadRequestException("Name Mandatory");
		}
	}

	public void validateBalance1M(AccountPostDto accpost) throws BadRequestException {
		if (accpost.getBalance().compareTo(new BigDecimal(1000000)) > 0) {
			logger.info("balance 1M regex");
		} else {
			logger.warn("insufience balance");
			throw new BadRequestException("insufience balance");
		}

	}

	public void validateBalance10000(AccountPostDto accountPostDto) throws BadRequestException {
		if (accountPostDto.getBalance().compareTo(new BigDecimal(10000)) > 0) {
			logger.info("balance 1M regex");
		} else {
			logger.warn("insufience balance");
			throw new BadRequestException("insufience balance");
		}

	}

	public void validateAccountIsPresent(AccountPostDto accpost) throws BadRequestException {
		Account account = repo.findByAccount(accpost.getAccount());
		if (account == null) {
			logger.info("succes validate is present");
		} else {
			logger.warn(accpost.getAccount() + " is exist");
			throw new BadRequestException(accpost.getAccount() + " is exist");
		}
	}

	public void validateStatusDevine(AccountPostDto accountPostDto) throws BadRequestException {
		if (accountPostDto.getStatus() == 0 || accountPostDto.getStatus() == 1) {
			logger.info("successs  status devine");
		} else {
			logger.error("Status not deviine");
			throw new BadRequestException("Status not deviine");
		}

	}

	public AccountDto validateAccountIsActive(AccountPostDto accountPostDto)
			throws BadRequestException, NotFoundException {
		AccountDto accountDto = this.findByAccountNumber(accountPostDto.getAccount());
		if (accountDto.getStatus() != 1) {
			logger.info("success account is active");
			return accountDto;
		} else {
			logger.warn("account not active");
			throw new BadRequestException("account not active");

		}
	}

	public AccountDto mappingAccountToAccountDto(Account account) {
		ModelMapper mapper = new ModelMapper();
		AccountDto accountDto2 = mapper.map(account, AccountDto.class);
		return accountDto2;
	}

	public String createAccount(AccountPostDto accpost) throws BadRequestException {
		this.validateRegexAccountNumber(accpost);
		this.validateAccountIsPresent(accpost);
		this.validateNameLength(accpost);
		this.validateBalance1M(accpost);
		Account account = new Account(null, accpost.getAccount(), accpost.getName(), accpost.getBalance(), 0.0, 0);
		repo.save(account);
		return "save";
	}

	public AccountDto updateBalance(AccountPostDto accountPostDto) throws NotFoundException, BadRequestException {
		this.validateRegexAccountNumber(accountPostDto);
		AccountDto accountDto = this.validateAccountIsActive(accountPostDto);
		this.validateBalance10000(accountPostDto);
		Account account = new Account(accountDto.getId(), accountPostDto.getAccount(), accountDto.getName(),
				accountDto.getBalance().add(accountPostDto.getBalance()), accountDto.getPoint(),
				accountDto.getStatus());
		repo.save(account);
		return mappingAccountToAccountDto(account);

	}

	public AccountDto updateBalance2(AccountPostDto accountPostDto) throws NotFoundException, BadRequestException {
		this.validateRegexAccountNumber(accountPostDto);
		AccountDto accountDto = this.validateAccountIsActive(accountPostDto);
		Account account = new Account(accountDto.getId(), accountPostDto.getAccount(), accountDto.getName(),
				accountDto.getBalance().add(accountPostDto.getBalance()), accountDto.getPoint(),
				accountDto.getStatus());
		repo.save(account);
		return mappingAccountToAccountDto(account);

	}
	public AccountDto transferPoint(AccountPostDto accountPostDto) throws NotFoundException, BadRequestException {
		this.validateRegexAccountNumber(accountPostDto);
		AccountDto accountDto = this.validateAccountIsActive(accountPostDto);
		Account account = new Account(accountDto.getId(), accountPostDto.getAccount(), accountDto.getName(),
				accountDto.getBalance().add(accountPostDto.getBalance()), accountPostDto.getPoint(),
				accountDto.getStatus());
		repo.save(account);
		return mappingAccountToAccountDto(account);

	}

	public AccountDto minBalance(AccountPostDto accountPostDto) throws NotFoundException, BadRequestException {
		this.validateRegexAccountNumber(accountPostDto);
		AccountDto accountDto = this.validateAccountIsActive(accountPostDto);
		Account account = new Account(accountDto.getId(), accountPostDto.getAccount(), accountDto.getName(),
				accountDto.getBalance().subtract(accountPostDto.getBalance()), accountPostDto.getPoint(),
				accountDto.getStatus());
		repo.save(account);
		return mappingAccountToAccountDto(account);
	}

	public AccountDto updateStatus(AccountPostDto accountPostDto) throws NotFoundException, BadRequestException {
		this.validateRegexAccountNumber(accountPostDto);
		AccountDto accountDto = this.findByAccountNumber(accountPostDto.getAccount());
		this.validateStatusDevine(accountPostDto);
		this.validateAccountIsActive(accountPostDto);
		Account account = new Account(accountDto.getId(), accountPostDto.getAccount(), accountDto.getName(),
				accountDto.getBalance(), accountDto.getPoint(), accountPostDto.getStatus());
		repo.save(account);
		return mappingAccountToAccountDto(account);
	}

	public AccountDto kurangPulsa(AccountPostDto accountPostDto) throws NotFoundException, BadRequestException {
		this.validateRegexAccountNumber(accountPostDto);
		AccountDto accountDto = this.findByAccountNumber(accountPostDto.getAccount());
		this.validateAccountIsActive(accountPostDto);
		Account account = new Account(accountDto.getId(), accountPostDto.getAccount(), accountDto.getName(),
				accountDto.getBalance(), accountDto.getPoint(), accountDto.getStatus());
		repo.save(account);
		ModelMapper mapper = new ModelMapper();
		AccountDto accountDto2 = mapper.map(account, AccountDto.class);
		return accountDto2;
	}
}
