package com.nttdata.reports.accountservice.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.reports.accountservice.model.BankAccounts;
import com.nttdata.reports.accountservice.model.CreditAccount;
import com.nttdata.reports.accountservice.model.ReportBankAccount;
import com.nttdata.reports.accountservice.service.ReportsAccountService;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@RequestMapping("/reports")
public class ReportsAccountController {

	@Autowired
	ReportsAccountService service;
	
	
	@GetMapping("/findBankAccount/{id}")
	public BankAccounts findByIdAccount(@PathVariable("id") Long id) {
		return service.findByIdAccount(id);
	}
	
	@GetMapping("/findCreditAccount/{id}")
	public CreditAccount findByIdCredit(@PathVariable("id") Long id) {
		return service.findByIdCredit(id);
	}
	
	@GetMapping(value= "/reportCommisions/", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<ReportBankAccount> commissionsChargedPerProduct(@RequestParam Long idProducto,@RequestParam Date from, @RequestParam Date to){
		
		return service.commissionsChargedPerProduct(idProducto,from, to);
	}
	
	
	
}
