package com.nttdata.reports.accountservice.FeignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nttdata.reports.accountservice.FeignClient.FallBack.CustomerFeignClientFallBack;
import com.nttdata.reports.accountservice.model.Customer;

@FeignClient(name="${api.customer-service.uri}", fallback = CustomerFeignClientFallBack.class)
public interface CustomerFeignClient {

	@GetMapping("/{id}")
	Customer customerfindById(@PathVariable(name = "id")Long id);
	
	@GetMapping
	List<Customer> findAllCustomers();
	
}
