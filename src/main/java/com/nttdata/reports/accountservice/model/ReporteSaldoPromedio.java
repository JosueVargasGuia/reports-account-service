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
public class ReporteSaldoPromedio {
	
	private Long idBankAccount;
	private Long idCreditAccount;
	private Double amount;
	private TypeAccount typeAccount;
	private Product product;

}
