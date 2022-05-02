package com.nttdata.reports.accountservice.FeignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import com.nttdata.reports.accountservice.FeignClient.FallBack.MovementAccountFeignClientFallBack;
import com.nttdata.reports.accountservice.model.MovementAccount;


@FeignClient(name = "${api.movement-account-service.uri}", fallback = MovementAccountFeignClientFallBack.class)
public interface MovementAccountFeignClient {
	
	@GetMapping
	List<MovementAccount> findAll();
	
}