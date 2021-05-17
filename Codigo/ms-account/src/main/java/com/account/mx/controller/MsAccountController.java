package com.account.mx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.account.mx.dto.AccountDto;
import com.account.mx.dto.TransactionDto;
import com.account.mx.service.IAccountService;

@RestController
@RequestMapping("/app/v1/demo")
public class MsAccountController {
	
	@Autowired
	private IAccountService poAccountService;

	@GetMapping("/acconts")
	public ResponseEntity<?> accounts() {
		List<AccountDto> laAccountDto = poAccountService.account(); 
		return new ResponseEntity<>(laAccountDto, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/tranfer")
	public ResponseEntity<?> tranfer(@RequestBody Map<String, Object> tranfer) {
		String lsResult = poAccountService.tranfer(Integer.parseInt(tranfer.get("fromAccount").toString()), Integer.parseInt(tranfer.get("toAccount").toString()), Double.parseDouble(tranfer.get("amount").toString()));
		Map<String, Object> loParam = new HashMap<String, Object>();
		loParam.put("Resultado", lsResult);
		return new ResponseEntity<>(loParam, HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/transacciones")
	public ResponseEntity<?> transacciones() {
		List<TransactionDto> laTransactionDto = poAccountService.transacciones();
		return new ResponseEntity<>(laTransactionDto, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/transacciones/from")
	public ResponseEntity<?> transaccionesByFromAccount(@RequestParam(name = "idAccount") Integer tiAccount) {
		List<TransactionDto> laTransactionDto = poAccountService.transaccionesByFromAccount(tiAccount);
		return new ResponseEntity<>(laTransactionDto, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/transacciones/to")
	public ResponseEntity<?> transaccionesByToAccount(@RequestParam(name = "idAccount") Integer tiAccount) {
		List<TransactionDto> laTransactionDto = poAccountService.transaccionesByToAccount(tiAccount);
		return new ResponseEntity<>(laTransactionDto, HttpStatus.ACCEPTED);
	}
	
}
