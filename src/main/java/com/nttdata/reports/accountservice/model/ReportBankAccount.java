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
public class ReportBankAccount {
	
	private MovementAccount movementAccount;
	private BankAccounts bankAccounts;
	private Product product;

}
