package com.nttdata.reports.accountservice.FeignClient.FallBack;

 

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.nttdata.reports.accountservice.FeignClient.MovementAccountFeignClient;
import com.nttdata.reports.accountservice.model.MovementAccount;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class MovementAccountFeignClientFallBack implements MovementAccountFeignClient {
	 
	public List<MovementAccount> findAll() {
		log.info("MovementAccountFeignClientFallBack: empty"   );
		return new ArrayList<MovementAccount>();
	}

}
