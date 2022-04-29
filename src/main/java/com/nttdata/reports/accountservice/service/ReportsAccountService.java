package com.nttdata.reports.accountservice.service;


import java.util.Map;

import com.nttdata.reports.accountservice.model.Account;
import com.nttdata.reports.accountservice.model.BankAccounts;
import com.nttdata.reports.accountservice.model.CreditAccount;

import reactor.core.publisher.Mono;

public interface ReportsAccountService {

	BankAccounts findByIdAccount(Long idBankAccount);
	
	CreditAccount findByIdCredit(Long idCreditAccount);
	
	Mono<Map<String, Object>> summaryWithAverageBalances(Account account);
	//Mono<Map<String, Object>> commissionsChargedPerProduct();
}
