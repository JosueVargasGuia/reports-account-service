package com.nttdata.reports.accountservice.FeignClient.FallBack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nttdata.reports.accountservice.FeignClient.ProductFeignClient;
import com.nttdata.reports.accountservice.model.Product;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class ProductFeignClientFallBack implements ProductFeignClient {

	@Value("${api.product-service.uri}")
	private String productService;

	public Product findById(Long id) {		 
		log.info("ProductFeignClientFallBack[" + productService + "/" + id + "]");
		return null;
	}

}
