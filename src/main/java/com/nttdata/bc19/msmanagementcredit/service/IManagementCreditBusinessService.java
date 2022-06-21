package com.nttdata.bc19.msmanagementcredit.service;

import com.nttdata.bc19.msmanagementcredit.model.CreditBusiness;
import com.nttdata.bc19.msmanagementcredit.request.CreditBusinessRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IManagementCreditBusinessService {

    Mono<CreditBusiness> create(CreditBusinessRequest creditBusinessRequest);
    Mono<CreditBusiness> update(CreditBusiness creditBusiness);
    Mono<Void>deleteById(String id);
    Mono<CreditBusiness> findById(String id);
    Flux<CreditBusiness> findAll();

}
