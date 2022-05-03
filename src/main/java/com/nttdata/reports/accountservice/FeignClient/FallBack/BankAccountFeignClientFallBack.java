package com.nttdata.reports.accountservice.FeignClient.FallBack;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.nttdata.reports.accountservice.FeignClient.BankAccountFeignClient;
import com.nttdata.reports.accountservice.model.BankAccounts;
import com.nttdata.reports.accountservice.model.ConsolidatedCustomerProducts;
import com.nttdata.reports.accountservice.model.MovementAccount;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class BankAccountFeignClientFallBack implements BankAccountFeignClient{

	@Value("${api.account-service.uri}")
	private String accountService;
	
	@Value("${api.movement-account-service.uri}")
	private String movementaccountService;
	
	@Override
	public BankAccounts bankAccountFindById(Long id) {
		log.info("AccountFeignClientFallBack -> " + accountService);
		return null;
	}

	@Override
	public MovementAccount consultMovementsAccount(Long idBankAccount) {
		log.info("AccountFeignClientFallBack -> " + movementaccountService);
		return null;
	}

	@Override
	public List<BankAccounts> findAllBankAccounts() {
		log.info("List of bank accounts not found! ");
		return null;
	}
	
	public List<ConsolidatedCustomerProducts> findProductByIdCustomer(Long idCustomer) {
		// TODO Auto-generated method stub
		log.info(accountService + "/findProductByIdCustomer/" + idCustomer);
		return new ArrayList<>();
	}
}
