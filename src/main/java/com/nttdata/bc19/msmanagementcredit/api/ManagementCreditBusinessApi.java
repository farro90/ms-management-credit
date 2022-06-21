package com.nttdata.bc19.msmanagementcredit.api;

import com.nttdata.bc19.msmanagementcredit.model.CreditBusiness;
import com.nttdata.bc19.msmanagementcredit.request.CreditBusinessRequest;
import com.nttdata.bc19.msmanagementcredit.service.IManagementCreditBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/credit/business")
public class ManagementCreditBusinessApi {

    @Autowired
    private IManagementCreditBusinessService managementCreditBusinessService;

    @PostMapping()
    public Mono<CreditBusiness> create(@RequestBody CreditBusinessRequest creditBusinessRequest){
        return managementCreditBusinessService.create(creditBusinessRequest);
    }

    @PutMapping()
    public Mono<CreditBusiness> update(@RequestBody CreditBusiness creditBusiness){
        return managementCreditBusinessService.update(creditBusiness);
    }

    @GetMapping()
    public Flux<CreditBusiness> findAll(){
        return managementCreditBusinessService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<CreditBusiness> findById(@PathVariable String id){ return managementCreditBusinessService.findById(id); }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteSA(@PathVariable String id){
        return managementCreditBusinessService.deleteById(id);
    }

}
