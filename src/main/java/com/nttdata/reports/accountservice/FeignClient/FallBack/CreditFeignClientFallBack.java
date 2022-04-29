package com.nttdata.reports.accountservice.FeignClient.FallBack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nttdata.reports.accountservice.FeignClient.CreditFeignClient;
import com.nttdata.reports.accountservice.model.CreditAccount;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class CreditFeignClientFallBack implements CreditFeignClient {
	
	@Value("${api.credit-service.uri}")
	String creditFeignClient;

	@Override
	public CreditAccount creditfindById(Long id) {
		log.info("CreditFeignClientFallBack ->" + creditFeignClient + "/" + id);
		return null;
	}

}
