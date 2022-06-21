package com.nttdata.bc19.msmanagementcredit.service.impl;

import com.nttdata.bc19.msmanagementcredit.exception.ModelNotFoundException;
import com.nttdata.bc19.msmanagementcredit.model.CreditBusiness;
import com.nttdata.bc19.msmanagementcredit.model.responseWC.ActiveProduct;
import com.nttdata.bc19.msmanagementcredit.model.responseWC.BusinessClient;
import com.nttdata.bc19.msmanagementcredit.repository.ICreditBusinessRepository;
import com.nttdata.bc19.msmanagementcredit.request.CreditBusinessRequest;
import com.nttdata.bc19.msmanagementcredit.service.IManagementCreditBusinessService;
import com.nttdata.bc19.msmanagementcredit.util.ActiveProductType;
import com.nttdata.bc19.msmanagementcredit.webclient.impl.ServiceWCImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class ManagementCreditBusinessServiceImpl implements IManagementCreditBusinessService {

    @Autowired
    ICreditBusinessRepository creditBusinessRepository;

    @Autowired
    private ServiceWCImpl clientServiceWC;

    @Override
    public Mono<CreditBusiness> create(CreditBusinessRequest creditBusinessRequest) {
        return clientServiceWC.findBusinessClientById(creditBusinessRequest.getIdBusinessClient())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(businessClientResponse ->
                        clientServiceWC.findActiveProductById(creditBusinessRequest.getIdActiveProduct())
                                .switchIfEmpty(Mono.error(new Exception()))
                                .flatMap(creditResponse -> this.businessLogicCurrentAccountBusiness(businessClientResponse, creditResponse, creditBusinessRequest))
                );
    }

    @Override
    public Mono<CreditBusiness> update(CreditBusiness creditBusiness) {
        creditBusiness.setUpdatedAt(LocalDateTime.now());
        return creditBusinessRepository.save(creditBusiness);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return creditBusinessRepository.deleteById(id);
    }

    @Override
    public Mono<CreditBusiness> findById(String id) {
        return creditBusinessRepository.findById(id);
    }

    @Override
    public Flux<CreditBusiness> findAll() {
        return creditBusinessRepository.findAll();
    }

    private Mono<CreditBusiness> businessLogicCurrentAccountBusiness(BusinessClient businessClient, ActiveProduct activeProduct, CreditBusinessRequest creditBusinessRequest){
        CreditBusiness creditBusiness = new CreditBusiness();
        creditBusiness.setId(new ObjectId().toString());
        creditBusiness.setCreatedAt(LocalDateTime.now());
        creditBusiness.setAmountGiven(creditBusinessRequest.getAmountGiven());
        creditBusiness.setInterestRate(creditBusinessRequest.getInterestRate());
        creditBusiness.setIdBusinessClient(creditBusinessRequest.getIdBusinessClient());
        creditBusiness.setIdActiveProduct(creditBusinessRequest.getIdActiveProduct());
        creditBusiness.setBusinessClient(businessClient);
        creditBusiness.setActiveProduct(activeProduct);

        if(activeProduct.getName().equals(ActiveProductType.CREDITO.name())){
            return Mono.error(new ModelNotFoundException("The active is not credit."));
        }
        if(!activeProduct.getAllowBusinessClient()) {
            return Mono.error(new ModelNotFoundException("Type of active not allowed for Business client"));
        }
        else{
            return creditBusinessRepository.save(creditBusiness);
        }
    }
}
