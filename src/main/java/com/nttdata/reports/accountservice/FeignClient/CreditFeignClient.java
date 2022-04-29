package com.nttdata.reports.accountservice.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nttdata.reports.accountservice.FeignClient.FallBack.CreditFeignClientFallBack;
import com.nttdata.reports.accountservice.model.CreditAccount;


@FeignClient(name="${api.credit-service.uri}", fallback = CreditFeignClientFallBack.class)
public interface CreditFeignClient {

	@GetMapping("/{id}")
	CreditAccount creditfindById(@PathVariable(name = "id") Long id);
}
