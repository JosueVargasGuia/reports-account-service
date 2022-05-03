package com.nttdata.reports.accountservice.FeignClient.FallBack;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nttdata.reports.accountservice.FeignClient.CustomerFeignClient;
import com.nttdata.reports.accountservice.model.Customer;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class CustomerFeignClientFallBack implements CustomerFeignClient {

	@Value("${api.customer-service.uri}")
	String customerService;

	public Customer customerfindById(Long id) {
		log.info("CustomerFeignClientFallBack: " + customerService + "/" + id);
		return null;

	}

	@Override
	public List<Customer> findAllCustomers() {
		log.info("List customers not found");
		return null;
	}

}
