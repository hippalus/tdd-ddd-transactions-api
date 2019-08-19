package com.transactions.rest.portweb.controller;

import com.transactions.rest.portweb.request.TransactionSaveRequest;
import com.transactions.rest.portweb.response.TransactionsSaveResponse;
import com.transactions.rest.portweb.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionSaveCommandController {

    private final TransactionService transactionService;


    public TransactionSaveCommandController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/save")
    public TransactionsSaveResponse save(@RequestBody @Valid TransactionSaveRequest request){
        return transactionService.save(request);

    }

}
