package com.atorres.nttdata.accountmsf.service.accountstrategy;

import com.atorres.nttdata.accountmsf.model.creditms.CreditDto;
import com.atorres.nttdata.accountmsf.model.dto.AccountDto;
import com.atorres.nttdata.accountmsf.utils.AccountCategory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountStrategy {
    Mono<Boolean> verifyAccount(Flux<AccountDto> listAccount);
    Mono<Boolean> verifyClient(Flux<AccountDto> listaAccount, Mono<AccountCategory> accountCategory, Flux<CreditDto> listCredit);
}
