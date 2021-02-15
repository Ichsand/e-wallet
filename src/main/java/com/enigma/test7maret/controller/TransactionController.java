package com.enigma.test7maret.controller;

import java.util.Date;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.enigma.test7maret.entity.Transaction;
import com.enigma.test7maret.entity.TransactionDto;
import com.enigma.test7maret.entity.TransactionPostDto;
import com.enigma.test7maret.exception.BadRequestException;
import com.enigma.test7maret.exception.ErrorDetails;
import com.enigma.test7maret.exception.NotFoundException;
import com.enigma.test7maret.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "E-Wallet Transaction Management")
@RequestMapping("/api/v1/")
public class TransactionController {

	
	@Autowired
	TransactionService service;
	
	@GetMapping("/transaction/search")
	@ApiOperation(value = "Get a list transaction by account number")
	public ResponseEntity<List<TransactionDto>> searchTransactionByAccount(
			@ApiParam(value = "Transaction from which Account will retrive") @RequestParam String account, @RequestParam String date) throws NotFoundException{
		return ResponseEntity.ok().body(service.searchTransactionFromAccount(account, date));
	}

	@PostMapping("/transaction/balance")
	@ApiOperation(value = "add balance")
	public ErrorDetails addBalance(
			@ApiParam(value = "Add balance from which Account Balance will retrive") @RequestBody TransactionPostDto dto, WebRequest req) throws NotFoundException, BadRequestException{
		service.addBalance(dto);
		ErrorDetails e = new ErrorDetails(new Date(),HttpStatus.OK.value(), "add balance success", req.getDescription(false));
		return e;
	}

	@PostMapping("/transaction/transfer")
	@ApiOperation(value = "transfer balance")
	public ErrorDetails treansfer(
			@ApiParam(value = "Transfer from which Account Balance will retrive") @RequestBody TransactionPostDto dto, WebRequest req) throws NotFoundException, BadRequestException{
		Transaction t =service.transfer(dto);
		ErrorDetails e = new ErrorDetails(new Date(),HttpStatus.OK.value(), "Transfer from "+t.getFrom().getAccount()+" to "+t.getTo().getAccount()+" success", req.getDescription(false));
		return e;
	}
	@PostMapping("/transaction/point")
	@ApiOperation(value = "transfer point")
	public ErrorDetails treansferPoint(
			@ApiParam(value = "Transfer from which Account Balance will retrive") @RequestBody TransactionPostDto dto, WebRequest req) throws NotFoundException, BadRequestException{
		Transaction t =service.transferPoint(dto);	
		ErrorDetails e = new ErrorDetails(new Date(),HttpStatus.OK.value(), "Transfer Point from "+t.getFrom().getAccount()+" success", req.getDescription(false));
		return e;
	}
	@PostMapping("/transaction/pulsa")
	@ApiOperation(value = "buy pulsa")
	public ErrorDetails buyPulsa(
			@ApiParam(value = "Buy Pulsa from which Account Balance will retrive") @RequestBody TransactionPostDto dto, WebRequest req) throws NotFoundException, BadRequestException{
		String [] arr=service.buyPulsa(dto);	
		ErrorDetails e = new ErrorDetails(new Date(),HttpStatus.OK.value(), "pulsa to "+arr[0]+" success , you got "+arr[1]+" point", req.getDescription(false));
		return e;
	}
	
	
}
