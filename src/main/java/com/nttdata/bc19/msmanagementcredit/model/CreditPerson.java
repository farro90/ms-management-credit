package com.nttdata.bc19.msmanagementcredit.model;

import com.nttdata.bc19.msmanagementcredit.model.responseWC.ActiveProduct;
import com.nttdata.bc19.msmanagementcredit.model.responseWC.PersonClient;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CreditPerson extends BaseModel{
    private double amountGiven;
    private double amountPaid;
    private double interestRate;
    private String idPersonClient;
    private String idActiveProduct;
    private PersonClient personClient;
    private ActiveProduct activeProduct;
}
