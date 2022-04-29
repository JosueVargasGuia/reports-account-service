package com.nttdata.reports.accountservice.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.reports.accountservice.FeignClient.AccountFeignClient;
import com.nttdata.reports.accountservice.FeignClient.CreditFeignClient;
import com.nttdata.reports.accountservice.FeignClient.TableIdFeignClient;
import com.nttdata.reports.accountservice.model.Account;
import com.nttdata.reports.accountservice.model.BankAccounts;
import com.nttdata.reports.accountservice.model.CreditAccount;
import com.nttdata.reports.accountservice.service.ReportsAccountService;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class ReportsAccountServiceImpl implements ReportsAccountService{
	
	@Autowired
	TableIdFeignClient tableIdFeignClient;
	
	@Autowired
	AccountFeignClient accountFeignClient;
	
	@Autowired
	CreditFeignClient creditFeignClient;
	
	@Override
	public BankAccounts findByIdAccount(Long idBankAccount) {
		BankAccounts accountBankAccounts = accountFeignClient.accountFindById(idBankAccount);
		return accountBankAccounts;
	}

	@Override
	public CreditAccount findByIdCredit(Long idCreditAccount) {
		CreditAccount credit = creditFeignClient.creditfindById(idCreditAccount);
		return credit;
	}

	@Override
	public Mono<Map<String, Object>> summaryWithAverageBalances(Account account) {
		
		Map<String, Object> hashMap = new HashMap<String, Object>();
		
		Account objAccount = this.findByIdAccount(account.getIdAccount());
		
		if(objAccount!=null) {
			
		}else {
			hashMap.put("Message", "Cuenta no encontrada");
			log.info("Message Cuenta no encontrada: "+ objAccount);
		}
		
		return null;
	}

}
