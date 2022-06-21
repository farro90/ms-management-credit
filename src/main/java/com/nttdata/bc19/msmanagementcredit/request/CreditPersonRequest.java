package com.nttdata.bc19.msmanagementcredit.request;

import lombok.Data;

@Data
public class CreditPersonRequest {
    private double amountGiven;
    private double interestRate;
    private String idPersonClient;
    private String idActiveProduct;
}
