package com.transactions.rest.portweb.service;

import com.transactions.aplicationlayer.*;
import com.transactions.domainkernel.PurchasingAgent;
import com.transactions.domainkernel.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TransactionService {

    @Autowired
    private TransactionSaveCommand transactionSaveCommand;

    @Autowired
    private TransactionSearchCommand transactionSearchCommand;


    public TransactionsSaveResponse save(TransactionSaveRequest transactionSaveRequest) {
        var saveInput = saveRequestToSaveInput(transactionSaveRequest);
        var saveOutput = transactionSaveCommand.exec(saveInput);
        return saveOutputToSaveResponse(saveOutput);

    }


    public TransactionSearchResponse search(TransactionSearchRequest transactionSearchRequest) {

        var searchInput = searchRequestToSearchInput(transactionSearchRequest);
        var searchOutput = transactionSearchCommand.exec(searchInput);

        return searchOutputToSearchResponse(searchOutput);


    }

    private TransactionSearchResponse searchOutputToSearchResponse(TransactionSearchOutput searchOutput) {
        return new TransactionSearchResponse(searchOutput.getTransactions().entrySet().stream()
                .flatMap(purchasingAgentListEntry -> purchasingAgentListEntry.getValue().stream())
                .map(transaction ->  TransactionResponse.builder()
                        .id(transaction.getId())
                        .agent(transaction.getAgent().getFullName())
                        .productName(transaction.getProductName())
                        .code(transaction.getCode())
                        .state(transaction.getState().toString())
                        .price(transaction.getPrice().getValue())
                        .build()
                        )
                .collect(Collectors.toList()));
    }

    private TransactionSearchInput searchRequestToSearchInput(TransactionSearchRequest transactionSearchRequest) {
        return new TransactionSearchInput(PurchasingAgent.aNew()
                .email(transactionSearchRequest.getAgent().getEmail())
                .firstName(transactionSearchRequest.getAgent().getFirstName())
                .lastName(transactionSearchRequest.getAgent().getLastName())
                .build());
    }

    private TransactionsSaveResponse saveOutputToSaveResponse(TransactionSaveOutput saveOutput) {
        return new TransactionsSaveResponse(saveOutput.getTransactionId(), saveOutput.getTransactionState());
    }

    private TransactionSaveInput saveRequestToSaveInput(TransactionSaveRequest transactionSaveRequest) {
        return TransactionSaveInput.builder()
                .email(transactionSaveRequest.getEmail())
                .firstName(transactionSaveRequest.getFirstName())
                .lastName(transactionSaveRequest.getLastName())
                .productName(transactionSaveRequest.getProductName())
                .productPrice(transactionSaveRequest.getProductPrice())
                .transactionCode(transactionSaveRequest.getTransactionCode())
                .build();
    }

}
