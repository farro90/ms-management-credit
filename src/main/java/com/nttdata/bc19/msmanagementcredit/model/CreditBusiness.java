package com.nttdata.bc19.msmanagementcredit.model;

import com.nttdata.bc19.msmanagementcredit.model.responseWC.ActiveProduct;
import com.nttdata.bc19.msmanagementcredit.model.responseWC.BusinessClient;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CreditBusiness extends BaseModel{
    private double amountGiven;
    private double amountPaid;
    private double interestRate;
    private String idBusinessClient;
    private String idActiveProduct;
    private BusinessClient businessClient;
    private ActiveProduct activeProduct;
}
