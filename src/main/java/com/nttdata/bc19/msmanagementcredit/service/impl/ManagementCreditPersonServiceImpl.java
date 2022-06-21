package com.nttdata.bc19.msmanagementcredit.service.impl;

import com.nttdata.bc19.msmanagementcredit.exception.ModelNotFoundException;
import com.nttdata.bc19.msmanagementcredit.model.CreditPerson;
import com.nttdata.bc19.msmanagementcredit.model.responseWC.ActiveProduct;
import com.nttdata.bc19.msmanagementcredit.model.responseWC.PersonClient;
import com.nttdata.bc19.msmanagementcredit.repository.ICreditPersonRepository;
import com.nttdata.bc19.msmanagementcredit.request.CreditPersonRequest;
import com.nttdata.bc19.msmanagementcredit.service.IManagementCreditPersonService;
import com.nttdata.bc19.msmanagementcredit.util.ActiveProductType;
import com.nttdata.bc19.msmanagementcredit.webclient.impl.ServiceWCImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class ManagementCreditPersonServiceImpl implements IManagementCreditPersonService {

    @Autowired
    ICreditPersonRepository creditPersonRepository;

    @Autowired
    private ServiceWCImpl clientServiceWC;

    @Override
    public Mono<CreditPerson> create(CreditPersonRequest creditPersonRequest) {
        return clientServiceWC.findPersonClientById(creditPersonRequest.getIdPersonClient())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(personClientResponse ->
                        clientServiceWC.findActiveProductById(creditPersonRequest.getIdActiveProduct())
                                .switchIfEmpty(Mono.error(new Exception()))
                                .flatMap(creditResponse -> this.businessLogicCurrentAccountPerson(personClientResponse, creditResponse, creditPersonRequest))
                        );
    }

    @Override
    public Mono<CreditPerson> update(CreditPerson creditPerson) {
        creditPerson.setUpdatedAt(LocalDateTime.now());
        return creditPersonRepository.save(creditPerson);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return creditPersonRepository.deleteById(id);
    }

    @Override
    public Mono<CreditPerson> findById(String id) {
        return creditPersonRepository.findById(id);
    }

    @Override
    public Flux<CreditPerson> findAll() {
        return creditPersonRepository.findAll();
    }

    private Mono<CreditPerson> businessLogicCurrentAccountPerson(PersonClient personClient, ActiveProduct activeProduct, CreditPersonRequest creditPersonRequest){
        CreditPerson creditPerson = new CreditPerson();
        creditPerson.setId(new ObjectId().toString());
        creditPerson.setCreatedAt(LocalDateTime.now());
        creditPerson.setAmountGiven(creditPersonRequest.getAmountGiven());
        creditPerson.setInterestRate(creditPersonRequest.getInterestRate());
        creditPerson.setIdPersonClient(creditPersonRequest.getIdPersonClient());
        creditPerson.setIdActiveProduct(creditPersonRequest.getIdActiveProduct());
        creditPerson.setPersonClient(personClient);
        creditPerson.setActiveProduct(activeProduct);

        if(activeProduct.getName().equals(ActiveProductType.CREDITO.name())){
            return Mono.error(new ModelNotFoundException("The active is not credit."));
        }
        if(!activeProduct.getAllowPersonClient()) {
            return Mono.error(new ModelNotFoundException("Type of active not allowed for person client"));
        }
        else{
            return creditPersonRepository.save(creditPerson);
        }
    }
}
