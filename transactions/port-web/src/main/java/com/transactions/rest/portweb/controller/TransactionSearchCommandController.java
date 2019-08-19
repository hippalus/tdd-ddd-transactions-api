package com.transactions.rest.portweb.controller;

import com.transactions.rest.portweb.request.TransactionSearchRequest;
import com.transactions.rest.portweb.response.TransactionSearchResponse;
import com.transactions.rest.portweb.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionSearchCommandController {

    private final TransactionService transactionService;

    public TransactionSearchCommandController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/search")
    public TransactionSearchResponse search(@RequestBody @Valid TransactionSearchRequest request){
        return transactionService.search(request);
    }
}
