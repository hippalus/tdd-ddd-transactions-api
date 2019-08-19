package com.transactions.aplicationlayer;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionSaveInput {

    private String firstName;

    private String lastName;

    private String email;

    private String transactionCode;

    private double productPrice;

    private String productName;

}
