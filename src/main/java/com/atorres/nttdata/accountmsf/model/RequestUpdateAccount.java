package com.atorres.nttdata.accountmsf.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestUpdateAccount {
    private BigDecimal balance;
    private String accountId;
}
