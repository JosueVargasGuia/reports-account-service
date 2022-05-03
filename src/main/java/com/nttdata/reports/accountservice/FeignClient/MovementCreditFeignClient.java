package com.nttdata.reports.accountservice.FeignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.nttdata.reports.accountservice.FeignClient.FallBack.MovementCreditFeignClientFallBack;
import com.nttdata.reports.accountservice.model.MovementCredit;


@FeignClient(name = "${api.movementCredit-service.uri}", fallback = MovementCreditFeignClientFallBack.class)
public interface MovementCreditFeignClient {
	
	@GetMapping
	List<MovementCredit> findAll();
	
}