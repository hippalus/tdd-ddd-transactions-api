package com.transactions.rest.portweb.response;

import com.transactions.domainkernel.TransactionState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionsSaveResponse {

    private Long transactionId;
    private TransactionState transactionState;

}
