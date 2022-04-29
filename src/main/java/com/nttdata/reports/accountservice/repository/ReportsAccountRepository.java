package com.nttdata.reports.accountservice.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.nttdata.reports.accountservice.model.Account;

@Repository
public interface ReportsAccountRepository extends ReactiveMongoRepository<Account, Long> {

}
