package com.atorres.nttdata.accountmsf.utils;

import com.atorres.nttdata.accountmsf.model.RequestAccount;
import com.atorres.nttdata.accountmsf.model.creditms.CreditDto;
import com.atorres.nttdata.accountmsf.model.creditms.RequestCredit;
import com.atorres.nttdata.accountmsf.model.dao.AccountDao;
import com.atorres.nttdata.accountmsf.model.dto.AccountDto;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper {
    public AccountDao accountToDao(RequestAccount requestAccount, String clientId){
        return AccountDao.builder()
                .id(generateId())
                .type(requestAccount.getType())
                .balance(requestAccount.getBalance())
                .accountCategory(requestAccount.getAccountCategory())
                .client(clientId)
                .build();
    }

    public AccountDto accountToDto(RequestAccount requestAccount, String clientId){
        AccountDto accountDto= new AccountDto();
        accountDto.setId(generateId());
        accountDto.setType(requestAccount.getType());
        accountDto.setAccountCategory(requestAccount.getAccountCategory());
        accountDto.setBalance(requestAccount.getBalance());
        accountDto.setClient(clientId);
        return accountDto;
    }
    public AccountDto accountToDto(AccountDao accountDao){
        AccountDto accountDto= new AccountDto();
        accountDto.setId(accountDao.getId());
        accountDto.setType(accountDao.getType());
        accountDto.setAccountCategory(accountDao.getAccountCategory());
        accountDto.setBalance(accountDao.getBalance());
        accountDto.setClient(accountDao.getClient());
        return accountDto;
    }

    private String generateId(){
        return java.util.UUID.randomUUID().toString().replace("-","");
    }
}
