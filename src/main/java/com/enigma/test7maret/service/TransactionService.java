package com.enigma.test7maret.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.test7maret.Repository.AccountRepository;
import com.enigma.test7maret.Repository.TransactionRepository;
import com.enigma.test7maret.dto.AccountPostDto;
import com.enigma.test7maret.entity.Account;
import com.enigma.test7maret.entity.Transaction;
import com.enigma.test7maret.entity.TransactionDto;
import com.enigma.test7maret.entity.TransactionPostDto;
import com.enigma.test7maret.exception.BadRequestException;
import com.enigma.test7maret.exception.NotFoundException;

@Service
public class TransactionService {

	private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

	@Autowired
	TransactionRepository repo;

	@Autowired
	AccountService accountService;

	@Autowired
	AccountRepository accountRepository;

	public List<TransactionDto> searchTransactionFromAccount(String account, String date) throws NotFoundException {

		Account account2 = accountRepository.findByAccount(account);
		List<TransactionDto> dtos = new ArrayList<TransactionDto>();
		if (account2 != null) {
			try {
				SimpleDateFormat format = new SimpleDateFormat("ddmmyyyy");
				Date date2 = format.parse(date);
				SimpleDateFormat formats = new SimpleDateFormat("yyyy-mm-dd");
				List<Transaction> transaction = repo.findAllByFromAndDateSQL(account2.getId(),
						java.sql.Date.valueOf(formats.format(date2)));
				for (Transaction trs : transaction) {
					TransactionDto dto = new TransactionDto(trs.getId(), trs.getTransactionDate(), trs.getType(),
							trs.getFrom().getAccount(), trs.getTo().getAccount(), trs.getAmount(),
							trs.getDescription());
					dtos.add(dto);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return dtos;
		} else {
			throw new NotFoundException(account + " is not found");
		}

	}

	@SuppressWarnings("null")
	public void validateAccountNotNull(Account account) throws NotFoundException {
		if (account != null) {
			logger.info("id " + account.getAccount() + " is exist");
		} else {
			logger.warn("Accoount " + account.getAccount() + " not found");
			throw new NotFoundException("Accoount " + account.getAccount() + " not found");
		}

	}

	public void validatePointsNull(Account from) throws BadRequestException {
		if (from.getPoint() > 0) {
			logger.info("point " + from.getPoint());
		} else {
			logger.warn("Point is 0 ");
			throw new BadRequestException("Point is 0");
		}
	}

	public void validateBalanceSufficientMoney(Account from, TransactionPostDto dto) throws BadRequestException {

		if (from.getBalance().compareTo(dto.getAmount()) >= 0) {
			logger.info("Accoount " + from.getAccount() + " sufficient money");
		} else {
			logger.warn("Accoount " + from.getAccount() + " not have sufficient money");
			throw new BadRequestException("Accoount " + from.getAccount() + " not have sufficient money");
		}
	}

	public java.sql.Date getDateSql() {
		java.util.Date udate = new Date();
		java.sql.Date date = new java.sql.Date(udate.getTime());
		return date;
	}

	public String addBalance(TransactionPostDto dto) throws NotFoundException, BadRequestException {
		Account account2 = accountRepository.findByAccount(dto.getAccount());
		validateAccountNotNull(account2);
		accountService.updateBalance2(
				new AccountPostDto(account2.getAccount(), account2.getName(), dto.getAmount(), account2.getStatus()));
		repo.save(new Transaction(this.getDateSql(), "balance", account2, account2, dto.getAmount(), "Add Balance"));
		return "save";
	}

	public Transaction transfer(TransactionPostDto dto) throws NotFoundException, BadRequestException {
		Account from = accountRepository.findByAccount(dto.getAccount());
		Account to = accountRepository.findByAccount(dto.getTo());
		validateAccountNotNull(from);
		validateAccountNotNull(to);
		validateBalanceSufficientMoney(from, dto);
		accountService
				.minBalance(new AccountPostDto(from.getAccount(), from.getName(), dto.getAmount(), from.getStatus()));
		accountService
				.updateBalance2(new AccountPostDto(to.getAccount(), to.getName(), dto.getAmount(), to.getStatus()));
		Transaction transaction = new Transaction(this.getDateSql(), "transfer", from, to, dto.getAmount(),
				dto.getDescription());
		repo.save(transaction);
		return transaction;
	}

	public Transaction transferPoint(TransactionPostDto dto) throws NotFoundException, BadRequestException {
		Account account2 = accountRepository.findByAccount(dto.getAccount());
		validateAccountNotNull(account2);
		validatePointsNull(account2);
		accountService
				.transferPoint(new AccountPostDto(account2.getAccount(), new BigDecimal(account2.getPoint()), 0.0));
		Transaction t = new Transaction(this.getDateSql(), "point", account2, account2,
				new BigDecimal(account2.getPoint()), "point to balance");
		repo.save(t);
		return t;
	}

	public Double getPoint(Double point) {
		if (point > 10000) {
			point = 10000.0;
			return point;
		} else {
			point = point * 0.1;
			return point;
		}
	}

	public String[] buyPulsa(TransactionPostDto dto) throws NotFoundException, BadRequestException {
		Account from = accountRepository.findByAccount(dto.getAccount());
		validateAccountNotNull(from);
		validateBalanceSufficientMoney(from, dto);
		Double point = dto.getAmount().doubleValue();
		point = getPoint(point);
		accountService.minBalance(
				new AccountPostDto(from.getAccount(), from.getName(), dto.getAmount(), from.getPoint() + point));
		repo.save(new Transaction(this.getDateSql(), "transfer", from, from, dto.getAmount(), dto.getHp()));
		String[] arr = { dto.getHp(), String.valueOf(point) };
		return arr;
	}
}
