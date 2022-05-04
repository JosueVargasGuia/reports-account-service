package com.nttdata.reports.accountservice.service;


import java.util.Date;


import com.nttdata.reports.accountservice.model.BankAccounts;
import com.nttdata.reports.accountservice.model.ConsolidatedCustomerProducts;
import com.nttdata.reports.accountservice.model.ProductId;
import com.nttdata.reports.accountservice.model.ReportBankAccount;
import com.nttdata.reports.accountservice.model.ReportBankProductInterval;
import com.nttdata.reports.accountservice.model.TypeAccount;

import reactor.core.publisher.Flux;

public interface ReportsAccountService {
	
	/*
	 * Metodo para generar un resumen con los saldos promedio diarios 
	 *	del mes en curso de todos los productos de crédito o cuentas bancarias que posee el cliente
	 * */
	
	Flux<BankAccounts> summaryWithAverageBalances(Long idCustomer, TypeAccount typeAccount);
	
	/*
	 *  Metodo para Generar un reporte de todas las comisiones cobradas por producto en un periodo 
	 *	de tiempo.
	 * 
	 * */
	Flux<ReportBankAccount> commissionsChargedPerProduct(Long idProducto, Date from, Date to);
	
	/*
	 * Método que reúne a la lista de productos bancarios y de crédito.
	 * 
	 * */
	
	Flux<ConsolidatedCustomerProducts> summaryForProduct(Long idCustomer);
	
	/*
	 * Metodo para generar un reporte general y completo por producto del banco en intervalo de tiempo 
	 * especificado por el usuario
	 * */
	
	Flux<ReportBankProductInterval> reportBankProductInterval(Long idCustomer, ProductId productId, Date from, Date to);

}
