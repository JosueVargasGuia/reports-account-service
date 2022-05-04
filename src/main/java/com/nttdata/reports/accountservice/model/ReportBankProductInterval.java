package com.nttdata.reports.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportBankProductInterval {

	private Customer customer;
	private Long idAccount;
	private Long idCustomer;
	private TypeAccount typeAccount;
	private Long idBankAccount;
	private Long idCreditAccount;
	private Product product;
	private BankAccounts bankAccounts;
	private CreditAccount creditAccount;
}
