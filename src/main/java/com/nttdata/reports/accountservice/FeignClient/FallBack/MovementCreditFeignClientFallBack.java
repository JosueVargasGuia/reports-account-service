package com.nttdata.reports.accountservice.FeignClient.FallBack;

 

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nttdata.reports.accountservice.FeignClient.MovementCreditFeignClient;
import com.nttdata.reports.accountservice.model.MovementCredit;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class MovementCreditFeignClientFallBack implements MovementCreditFeignClient {
	 
	public List<MovementCredit> findAll() {
		
		log.info("MovementCreditFeignClientFallBack: empty"   );
		return new ArrayList<MovementCredit>();
	}

}
