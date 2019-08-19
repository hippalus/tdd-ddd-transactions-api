package com.transactions.rest.portweb.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionSaveRequest {
    private String firstName;

    private String lastName;

    private String email;

    private String transactionCode;

    private double productPrice;

    private String productName;



}
