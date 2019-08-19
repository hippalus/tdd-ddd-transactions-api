package com.transactions.rest.portweb.service;

import com.transactions.aplicationlayer.*;
import com.transactions.domainkernel.*;

import com.transactions.rest.portweb.request.AgentToSearch;
import com.transactions.rest.portweb.request.TransactionSaveRequest;
import com.transactions.rest.portweb.request.TransactionSearchRequest;
import com.transactions.rest.portweb.response.TransactionResponse;
import com.transactions.rest.portweb.response.TransactionSearchResponse;
import com.transactions.rest.portweb.response.TransactionsSaveResponse;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
public class TransactionsServiceTest {

    @Mock
    private TransactionSaveCommand transactionSaveCommand;

    @Mock
    private TransactionSearchCommand transactionSearchCommand;

    @InjectMocks
    private TransactionService transactionService;


    @Test
    void save() {

        var saveInput = new TransactionSaveInput("John", "Doe", "john.doe@gmail.com", "TR045", 15d, "Product 1");
        var saveOutput = new TransactionSaveOutput(1L, TransactionState.APPROVED);
        when(transactionSaveCommand.exec(saveInput)).thenReturn(saveOutput);

        var transactionSaveRequest = new TransactionSaveRequest("John", "Doe", "john.doe@gmail.com", "TR045", 15d, "Product 1");
        var transactionSaveResponse = new TransactionsSaveResponse(1L, TransactionState.APPROVED);

        assertEquals(transactionSaveResponse, transactionService.save(transactionSaveRequest));

    }

    @Test
    void search() {
        var agent = PurchasingAgent.aNew()
                .email("john.doe@gmail.com")
                .firstName("John")
                .lastName("Doe")
                .build();

        var transaction = Transaction.aNew()
                .agent(agent)
                .product(Product.aNew().name("Product 1").price(Money.of(20d)).get())
                .withCode("TR123")
                .build();

        var transactionSearchInput= new TransactionSearchInput(agent);
        var searchResult= Map.of(agent, List.of(transaction));
        var transactionSearchOutput=new TransactionSearchOutput(searchResult);
        when(transactionSearchCommand.exec(transactionSearchInput)).thenReturn(transactionSearchOutput);

        var request=new TransactionSearchRequest(AgentToSearch.builder()
                .email("john.doe@gmail.com")
                .firstName("John")
                .lastName("Doe")
                .build());

        var response=new TransactionSearchResponse(Collections.singletonList(TransactionResponse.builder()
                .agent(transaction.getAgent().getFullName())
                .code(transaction.getCode())
                .productName(transaction.getProductName())
                .price(transaction.getPrice().getValue())
                .id(transaction.getId())
                .state(transaction.getState().toString())
                .build()));

        assertEquals(response.getTransactions(),transactionService.search(request).getTransactions());











    }

}
