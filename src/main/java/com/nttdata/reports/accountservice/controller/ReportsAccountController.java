package com.nttdata.reports.accountservice.controller;

import java.util.Date;

import javax.websocket.server.PathParam;

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
import com.nttdata.reports.accountservice.model.TypeAccount;
import com.nttdata.reports.accountservice.service.ReportsAccountService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/reports")
public class ReportsAccountController {

	@Autowired
	ReportsAccountService service;
	
	@GetMapping(value= "/reportCommisions/", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<ReportBankAccount> commissionsChargedPerProduct(@RequestParam Long idProducto,@RequestParam Date from, @RequestParam Date to){
		
		return service.commissionsChargedPerProduct(idProducto,from, to);
	}
	
	@GetMapping("/summaryWithAverageBalances/{idCustomer}")
	public Flux summaryWithAverageBalances(@PathVariable("idCustomer") Long idCustomer, @RequestParam TypeAccount typeAccount){
		return service.summaryWithAverageBalances(idCustomer, typeAccount);
	}
	
}
