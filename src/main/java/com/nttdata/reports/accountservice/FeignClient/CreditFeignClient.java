package com.nttdata.reports.accountservice.FeignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nttdata.reports.accountservice.FeignClient.FallBack.CreditFeignClientFallBack;
import com.nttdata.reports.accountservice.model.ConsolidatedCustomerProducts;
import com.nttdata.reports.accountservice.model.CreditAccount;


@FeignClient(name = "${api.credit-service.uri}", fallback = CreditFeignClientFallBack.class)
public interface CreditFeignClient {

	@GetMapping("/findProductByIdCustomer/{idCustomer}")
	List<ConsolidatedCustomerProducts> findProductByIdCustomer(@PathVariable("idCustomer") Long idCustomer);
	
	@GetMapping
	List<CreditAccount> findAllCreditsAccounts();
}
