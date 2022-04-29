package com.nttdata.reports.accountservice.FeignClient.FallBack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.nttdata.reports.accountservice.FeignClient.AccountFeignClient;
import com.nttdata.reports.accountservice.model.BankAccounts;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class AccountFeignClientFallBack implements AccountFeignClient{

	@Value("${api.account-service.uri}")
	private String accountService;
	
	@Override
	public BankAccounts accountFindById(Long id) {
		log.info("AccountFeignClientFallBack -> " + accountService);
		return null;
	}
}
