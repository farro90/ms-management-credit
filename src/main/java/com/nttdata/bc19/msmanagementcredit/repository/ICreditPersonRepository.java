package com.nttdata.bc19.msmanagementcredit.repository;

import com.nttdata.bc19.msmanagementcredit.model.CreditPerson;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ICreditPersonRepository extends ReactiveMongoRepository<CreditPerson, String> {
    Flux<CreditPerson> findByIdPersonClient(String id);

    Mono<Long> countByIdPersonClient(String id);

    Flux<CreditPerson> findByIdPersonClientAndIdActiveProduct(String idPersonClient, String idPasiveProduct);

    Mono<Long> countByIdPersonClientAndIdActiveProduct(String idPersonClient, String idPasiveProduct);
}
