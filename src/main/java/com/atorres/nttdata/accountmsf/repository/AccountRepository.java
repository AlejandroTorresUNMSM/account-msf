package com.atorres.nttdata.accountmsf.repository;

import com.atorres.nttdata.accountmsf.model.dao.AccountDao;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<AccountDao,String> {
}
