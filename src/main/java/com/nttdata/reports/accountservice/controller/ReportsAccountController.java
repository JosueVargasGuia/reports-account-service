package com.nttdata.reports.accountservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.reports.accountservice.model.BankAccounts;
import com.nttdata.reports.accountservice.model.CreditAccount;
import com.nttdata.reports.accountservice.service.ReportsAccountService;

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
	
	
	
}
