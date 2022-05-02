package com.nttdata.reports.accountservice.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.reports.accountservice.FeignClient.BankAccountFeignClient;
import com.nttdata.reports.accountservice.FeignClient.CreditFeignClient;
import com.nttdata.reports.accountservice.FeignClient.MovementAccountFeignClient;
import com.nttdata.reports.accountservice.FeignClient.ProductFeignClient;
import com.nttdata.reports.accountservice.FeignClient.TableIdFeignClient;
import com.nttdata.reports.accountservice.model.BankAccounts;
import com.nttdata.reports.accountservice.model.CreditAccount;
import com.nttdata.reports.accountservice.model.ReportBankAccount;
import com.nttdata.reports.accountservice.service.ReportsAccountService;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class ReportsAccountServiceImpl implements ReportsAccountService {

	@Autowired
	TableIdFeignClient tableIdFeignClient;

	@Autowired
	BankAccountFeignClient bankAccountFeignClient;

	@Autowired
	CreditFeignClient creditFeignClient;
	
	@Autowired
	ProductFeignClient productFeignClient;
	
	@Autowired
	MovementAccountFeignClient movementAccountFeignClient;

	@Override
	public BankAccounts findByIdAccount(Long idBankAccount) {
		BankAccounts accountBankAccounts = bankAccountFeignClient.bankAccountFindById(idBankAccount);
		return accountBankAccounts;
	}

	@Override
	public CreditAccount findByIdCredit(Long idCreditAccount) {
		CreditAccount credit = creditFeignClient.creditfindById(idCreditAccount);
		return credit;
	}

	@Override
	public Mono<Map<String, Object>> summaryWithAverageBalances(BankAccounts bankAccounts ) {
		
		Map<String, Object> hashMap = new HashMap<String, Object>();
		
		BankAccounts objBankAccount = this.findByIdAccount(bankAccounts.getIdBankAccount());
		
		if(objBankAccount!=null) {
			
			
		}else {
			hashMap.put("Message", "Cuenta no encontrada");
			log.info("Message Cuenta no encontrada: "+ objBankAccount);
		}
		
		return null;
	}

	
	/*
	 * Método para obtener consultas de reporte de todas las comisiones cobradas por producto en un periodo 
	 *	de tiempo.
	 * */
	
	@SuppressWarnings("deprecation")
	@Override
	public Flux<ReportBankAccount> commissionsChargedPerProduct(Long idProducto, Date from, Date to) {
		return Flux.fromIterable(movementAccountFeignClient.findAll())
			.filter(movAccount-> movAccount.getDateMovementAccount().getDate()>=from.getDate() && movAccount.getDateMovementAccount().getDate()<=to.getDate())
			.map(obj-> {
				ReportBankAccount rba = new ReportBankAccount();
				rba.setMovementAccount(obj);
				log.info("OBJ: "+ obj);
				BankAccounts accounts = bankAccountFeignClient.bankAccountFindById(obj.getIdBankAccount());
				log.info("accounts: "+ accounts);
				rba.setBankAccounts(accounts);
				rba.setProduct(productFeignClient.findById(accounts.getIdProduct()));
				log.info("Product: "+ accounts.getIdProduct());
				log.info("RBA: "+ rba.toString());
				return rba;
			})
			.filter(x-> x.getProduct().getIdProducto() == idProducto);
	}

	
	


	
	
}
