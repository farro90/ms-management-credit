package com.nttdata.bc19.msmanagementcredit.service;

import com.nttdata.bc19.msmanagementcredit.model.CreditPerson;
import com.nttdata.bc19.msmanagementcredit.request.CreditPersonRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IManagementCreditPersonService {

    Mono<CreditPerson> create(CreditPersonRequest creditPersonRequest);
    Mono<CreditPerson> update(CreditPerson creditPerson);
    Mono<Void>deleteById(String id);
    Mono<CreditPerson> findById(String id);
    Flux<CreditPerson> findAll();

}
