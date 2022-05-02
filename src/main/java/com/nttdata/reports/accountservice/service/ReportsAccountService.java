package com.nttdata.reports.accountservice.service;


import java.util.Date;
import java.util.Map;

import com.nttdata.reports.accountservice.model.BankAccounts;
import com.nttdata.reports.accountservice.model.CreditAccount;
import com.nttdata.reports.accountservice.model.ReportBankAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReportsAccountService {

	BankAccounts findByIdAccount(Long idBankAccount);
	
	CreditAccount findByIdCredit(Long idCreditAccount);
	
	Mono<Map<String, Object>> summaryWithAverageBalances(BankAccounts bankAccounts);
	
	/*
	 *  Metodo para Generar un reporte de todas las comisiones cobradas por producto en un periodo 
	 *	de tiempo.
	 * 
	 * */
	Flux<ReportBankAccount> commissionsChargedPerProduct(Long idProducto, Date from, Date to);
}
