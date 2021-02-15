package com.enigma.test7maret.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.test7maret.dto.AccountDto;
import com.enigma.test7maret.dto.AccountPostDto;
import com.enigma.test7maret.exception.BadRequestException;
import com.enigma.test7maret.exception.NotFoundException;
import com.enigma.test7maret.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "E-Wallet Account Management")
@RequestMapping("/api/v1/")
public class AccountController {

	@Autowired
	AccountService service;

	@GetMapping("/account/{accountNumber}")
	@ApiOperation(value = "Get Account by their Account Number")
	public ResponseEntity<AccountDto> findByAccountNumber(
			@ApiParam(value = "Account Number from which Account will retrieve") @PathVariable String accountNumber)
			throws NotFoundException {
		return ResponseEntity.ok().body(service.findByAccountNumber(accountNumber));
	}

	@GetMapping("/account/search")
	@ApiOperation(value = "Get Account by their Account Number")
	public ResponseEntity<List<AccountDto>> findByName(
			@ApiParam(value = "Name from which Account will retrieve") @RequestParam String name ) throws NotFoundException {
		return ResponseEntity.ok().body(service.findByName(name));
	}
	
	@PostMapping("/account")
	@ApiOperation(value = "Create Account")
	public ResponseEntity<String> createAccount(
			@ApiParam(value = "Create a new Account") @RequestBody AccountPostDto accpost) throws BadRequestException{
		service.createAccount(accpost);
		return ResponseEntity.ok().body("Save");
	}
	
	@PutMapping("/account/balance")
	@ApiOperation(value = "Update Balance")
	public ResponseEntity<AccountDto> updateBalance(
			@ApiParam(value = "Update Balance") @RequestBody AccountPostDto accountPostDto) throws NotFoundException, BadRequestException{
		return ResponseEntity.ok().body(service.updateBalance(accountPostDto));
	}
	
	@PutMapping("/account/status")
	@ApiOperation(value = "Update Status")
	public ResponseEntity<AccountDto> updateStatus(
			@ApiParam(value = "Update Status") @RequestBody AccountPostDto accountPostDto) throws NotFoundException, BadRequestException{
		return ResponseEntity.ok().body(service.updateStatus(accountPostDto));
	}

}
