package com.nttdata.reports.accountservice.FeignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.nttdata.reports.accountservice.FeignClient.FallBack.BankAccountFeignClientFallBack;
import com.nttdata.reports.accountservice.model.BankAccounts;
import com.nttdata.reports.accountservice.model.MovementAccount;


@FeignClient(name = "${api.account-service.uri}", fallback = BankAccountFeignClientFallBack.class)
public interface BankAccountFeignClient {
	
	@GetMapping("/{id}")
	BankAccounts bankAccountFindById(@PathVariable("id") Long id);
	
	@GetMapping(value = "/consultMovementsAccount/{idBankAccount}")
	MovementAccount consultMovementsAccount(@PathVariable("idBankAccount") Long idBankAccount);
	
	@GetMapping
	List<BankAccounts> findAllBankAccounts();


	
}