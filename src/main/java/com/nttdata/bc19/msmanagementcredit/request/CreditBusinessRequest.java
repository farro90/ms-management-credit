package com.nttdata.bc19.msmanagementcredit.request;

import lombok.Data;

import java.util.List;

@Data
public class CreditBusinessRequest {
    private double amountGiven;
    private double interestRate;
    private String idBusinessClient;
    private String idActiveProduct;
}
