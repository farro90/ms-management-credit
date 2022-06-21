package com.nttdata.bc19.msmanagementcredit.repository;

import com.nttdata.bc19.msmanagementcredit.model.CreditBusiness;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ICreditBusinessRepository extends ReactiveMongoRepository<CreditBusiness, String> {
    Flux<CreditBusiness> findByIdBusinessClient(String id);

    Mono<Long> countByIdBusinessClient(String id);

    Flux<CreditBusiness> findByIdBusinessClientAndIdActiveProduct(String idBusinessClient, String idPasiveProduct);

    Mono<Long> countByIdBusinessClientAndIdActiveProduct(String idBusinessClient, String idPasiveProduct);
}
