package com.nttdata.bc19.msmanagementcredit.api;

import com.nttdata.bc19.msmanagementcredit.model.CreditPerson;
import com.nttdata.bc19.msmanagementcredit.request.CreditPersonRequest;
import com.nttdata.bc19.msmanagementcredit.service.IManagementCreditPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/credit/person")
public class ManagementCreditPersonApi {

    @Autowired
    private IManagementCreditPersonService managementCreditPersonService;

    @PostMapping()
    public Mono<CreditPerson> create(@RequestBody CreditPersonRequest creditPersonRequest){
        return managementCreditPersonService.create(creditPersonRequest);
    }

    @PutMapping()
    public Mono<CreditPerson> update(@RequestBody CreditPerson creditPerson){
        return managementCreditPersonService.update(creditPerson);
    }

    @GetMapping()
    public Flux<CreditPerson> findAll(){
        return managementCreditPersonService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<CreditPerson> findById(@PathVariable String id){ return managementCreditPersonService.findById(id); }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteSA(@PathVariable String id){
        return managementCreditPersonService.deleteById(id);
    }
}
