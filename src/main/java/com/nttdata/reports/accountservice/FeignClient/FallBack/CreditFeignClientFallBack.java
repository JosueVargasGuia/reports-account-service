package com.nttdata.reports.accountservice.FeignClient.FallBack;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nttdata.reports.accountservice.FeignClient.CreditFeignClient;
import com.nttdata.reports.accountservice.model.ConsolidatedCustomerProducts;
import com.nttdata.reports.accountservice.model.CreditAccount;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class CreditFeignClientFallBack implements CreditFeignClient {
	
	@Value("${api.credit-service.uri}")
	private String service;

	public List<ConsolidatedCustomerProducts> findProductByIdCustomer(Long idCustomer) {
		// TODO Auto-generated method stub
		log.info(service + "/findProductByIdCustomer/" + idCustomer);
		return new ArrayList<>();
	}

	@Override
	public List<CreditAccount> findAllCreditsAccounts() {
		log.info("List of credits accounts not found! ");
		return null;
	}
	
	@Override
	public CreditAccount creditAccountFindById(Long id) {
		log.info("CreditAccountFeignClientFallBack -> " + service + "/"+ id);
		return null;
	}
}
