package com.nttdata.reports.accountservice.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nttdata.reports.accountservice.FeignClient.BankAccountFeignClient;
import com.nttdata.reports.accountservice.FeignClient.CreditFeignClient;
import com.nttdata.reports.accountservice.FeignClient.CustomerFeignClient;
import com.nttdata.reports.accountservice.FeignClient.MovementAccountFeignClient;
import com.nttdata.reports.accountservice.FeignClient.MovementCreditFeignClient;
import com.nttdata.reports.accountservice.FeignClient.ProductFeignClient;
import com.nttdata.reports.accountservice.FeignClient.TableIdFeignClient;
import com.nttdata.reports.accountservice.model.BankAccounts;
import com.nttdata.reports.accountservice.model.ConsolidatedCustomerProducts;
import com.nttdata.reports.accountservice.model.CreditAccount;
import com.nttdata.reports.accountservice.model.Customer;
import com.nttdata.reports.accountservice.model.ProductId;
import com.nttdata.reports.accountservice.model.ReportBankAccount;
import com.nttdata.reports.accountservice.model.ReportBankProductInterval;
import com.nttdata.reports.accountservice.model.ReporteSaldoPromedio;
import com.nttdata.reports.accountservice.model.TypeAccount;
import com.nttdata.reports.accountservice.service.ReportsAccountService;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

@Log4j2
@Service
public class ReportsAccountServiceImpl implements ReportsAccountService {

	@Autowired
	TableIdFeignClient tableIdFeignClient;

	@Autowired
	BankAccountFeignClient bankAccountFeignClient;

	@Autowired
	CreditFeignClient creditFeignClient;

	@Autowired
	ProductFeignClient productFeignClient;

	@Autowired
	MovementAccountFeignClient movementAccountFeignClient;

	@Autowired
	CustomerFeignClient customerFeignClient;

	@Autowired
	MovementCreditFeignClient movementCreditFeignClient;

	@Override
	public Flux summaryWithAverageBalances(Long idCustomer, TypeAccount typeAccount) {

		return this.summaryForProduct(idCustomer).map(ccp -> {// ConsolideCustomerProduct

			if (ccp.getTypeAccount() == typeAccount) {
				return this.movementAccountFeignClient.findAll().stream()
						.filter(x -> x.getIdBankAccount() == ccp.getIdBankAccount()).map(movAccount -> {
							ReporteSaldoPromedio rpt = new ReporteSaldoPromedio();
							rpt.setIdBankAccount(movAccount.getIdBankAccount());
							rpt.setTypeAccount(typeAccount);
							rpt.setProduct(ccp.getProduct());
							rpt.setIdCreditAccount(ccp.getIdCreditAccount());

							rpt.setAmount(movAccount.getAmount());
							log.info("TypeAccount : " + typeAccount);
							log.info("rpt: " + rpt.toString());
							return rpt;
						});
			} else {
				return this.movementCreditFeignClient.findAll().stream()
						.filter(x -> x.getIdCreditAccount() == ccp.getIdCreditAccount()).map(movCredit -> {
							ReporteSaldoPromedio rpt = new ReporteSaldoPromedio();
							rpt.setIdBankAccount(ccp.getIdBankAccount());
							rpt.setTypeAccount(typeAccount);
							rpt.setProduct(ccp.getProduct());
							rpt.setIdCreditAccount(movCredit.getIdCreditAccount());

							rpt.setAmount(movCredit.getAmount());
							log.info("TypeAccount : " + typeAccount);
							log.info("rpt: " + rpt.toString());
							return rpt;
						});
			}

		});

	};

	/*
	 * Método para obtener consultas de reporte de todas las comisiones cobradas por
	 * producto en un periodo de tiempo.
	 */

	@SuppressWarnings("deprecation")
	@Override
	public Flux<ReportBankAccount> commissionsChargedPerProduct(Long idProducto, Date from, Date to) {
		return Flux.fromIterable(movementAccountFeignClient.findAll())
				.filter(movAccount -> movAccount.getDateMovementAccount().getDate() >= from.getDate()
						&& movAccount.getDateMovementAccount().getDate() <= to.getDate())
				.map(obj -> {
					ReportBankAccount rba = new ReportBankAccount();
					rba.setMovementAccount(obj);
					log.info("OBJ: " + obj);
					BankAccounts accounts = bankAccountFeignClient.bankAccountFindById(obj.getIdBankAccount());
					log.info("accounts: " + accounts);
					rba.setBankAccounts(accounts);
					rba.setProduct(productFeignClient.findById(accounts.getIdProduct()));
					log.info("Product: " + accounts.getIdProduct());
					log.info("RBA: " + rba.toString());
					return rba;
				}).filter(x -> x.getProduct().getIdProducto() == idProducto);
	}

	/*
	 * Método que retorna una lista total de los productos de credito o cuentas
	 * bancarias del cliente.
	 */

	@Override
	public Flux<ConsolidatedCustomerProducts> summaryForProduct(Long idCustomer) {
		List<ConsolidatedCustomerProducts> listaAccount = bankAccountFeignClient.findProductByIdCustomer(idCustomer);
		List<ConsolidatedCustomerProducts> listaCredit = creditFeignClient.findProductByIdCustomer(idCustomer);
		listaAccount.addAll(listaCredit);
		listaAccount.forEach(e -> log.info("ConsolidatedCustomerProducts:" + e.toString()));
		return Flux.fromIterable(listaAccount);
	}

	/*
	 * Metodo para generar un reporte general y completo por producto del banco en
	 * intervalo de tiempo especificado por el usuario
	 */

	@SuppressWarnings("deprecation")
	@Override
	public Flux<ReportBankProductInterval> reportBankProductInterval(Long idCustomer, ProductId productId, Date from,
			Date to) {
		return this.summaryForProduct(idCustomer)
				.filter(product -> product.getProduct().getCreationDate().getDate() >= from.getDate() //filtrando rango de fechas especificado por el usuario
						&& product.getProduct().getCreationDate().getDate() <= to.getDate())
				.filter(prodType -> productId.equals(prodType.getProduct().getProductId()))
				.map(obj -> {
					ReportBankProductInterval rbpi = new ReportBankProductInterval();
						rbpi.setProduct(obj.getProduct());
						rbpi.setTypeAccount(obj.getTypeAccount());
						rbpi.setIdCustomer(obj.getIdCustomer());
						rbpi.setIdAccount(obj.getIdAccount());
						rbpi.setIdBankAccount(obj.getIdBankAccount());
						rbpi.setIdCreditAccount(obj.getIdCreditAccount());
						rbpi.setCreditAccount(obj.getCreditAccount());
						Customer cust = customerFeignClient.customerfindById(idCustomer);
						BankAccounts ba = bankAccountFeignClient.bankAccountFindById(obj.getIdBankAccount());
						CreditAccount ca = creditFeignClient.creditAccountFindById(obj.getIdCreditAccount());
						rbpi.setBankAccounts(ba);
						rbpi.setCustomer(cust);
						rbpi.setCreditAccount(ca);
						log.info("Product OBJ: " + obj);
						log.info("ReportBankProductInterval: " + rbpi);

						return rbpi;

				});
	}

}
