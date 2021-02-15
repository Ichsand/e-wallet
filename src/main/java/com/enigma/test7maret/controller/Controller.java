//package com.enigma.test7maret.controller;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.enigma.test7maret.entity.PulsaDto;
//import com.enigma.test7maret.exception.ErrorDetails;
//import com.enigma.test7maret.exception.MyFileNotFoundException;
//import com.enigma.test7maret.service.PulsaService;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//
//@RestController
//@Api(value = "Management Pulsa")
//public class Controller {
//	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
//
//	@Autowired
//	private PulsaService service;
//	
//	@GetMapping("/pulsa")
//	@ApiOperation(value = "View a list of all pulsa", response = PulsaDto.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "Success"),
//			@ApiResponse(code = 404, message = "Not Found", response = ErrorDetails.class) })
//	public ResponseEntity<List<PulsaDto>> findAll(){
//		return ResponseEntity.ok().body(service.findAll());
//		
//	}
//	
//	@GetMapping("/pulsa/{id}")
//	@ApiOperation(value = "Get pulsa by their id")
//	public ResponseEntity<PulsaDto> getPulsaById(
//			@ApiParam(value = "Pulsa id from which pulsa will retrieve") @PathVariable Long id) throws MyFileNotFoundException {
//		return ResponseEntity.ok().body(service.findPulsaById(id));
//	}
//	
//	
//	
//}
