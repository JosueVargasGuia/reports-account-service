package com.nttdata.reports.accountservice.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nttdata.reports.accountservice.FeignClient.FallBack.AccountFeignClientFallBack;
import com.nttdata.reports.accountservice.model.BankAccounts;


@FeignClient(name = "${api.account-service.uri}", fallback = AccountFeignClientFallBack.class)
public interface AccountFeignClient {
	
	@GetMapping("/{id}")
	BankAccounts accountFindById(@PathVariable("id") Long id);
	
}
