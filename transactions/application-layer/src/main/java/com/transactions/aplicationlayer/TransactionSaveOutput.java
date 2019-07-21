package com.transactions.aplicationlayer;

import com.transactions.domainkernel.TransactionState;
import lombok.Data;

@Data
public class TransactionSaveOutput {
    private Long transactionId;
    private TransactionState transactionState;

    public TransactionSaveOutput(Long transactionId, TransactionState transactionState) {
        this.transactionId = transactionId;
        this.transactionState = transactionState;
    }
}
