package com.atorres.nttdata.accountmsf.model.dao;

import com.atorres.nttdata.accountmsf.utils.AccountCategory;
import com.atorres.nttdata.accountmsf.utils.AccountType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document("accounts")
@Builder
@ToString
public class AccountDao {
    @Id
    private String id;
    private AccountType type;
    private BigDecimal balance;
    private AccountCategory accountCategory;
    private String client;
}
