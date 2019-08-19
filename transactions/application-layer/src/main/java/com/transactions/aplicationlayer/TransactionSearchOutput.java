package com.transactions.aplicationlayer;

import com.transactions.domainkernel.PurchasingAgent;
import com.transactions.domainkernel.Transaction;

import java.util.List;
import java.util.Map;

public class TransactionSearchOutput {
    private Map<PurchasingAgent, List<Transaction>> transactions;

    public TransactionSearchOutput(Map<PurchasingAgent, List<Transaction>> transactions) {
        this.transactions = transactions;
    }

    public Map<PurchasingAgent, List<Transaction>> getTransactions() {
        return transactions;
    }

    public int getAgentsCount() {
        return this.transactions.size();
    }

    public int getTransactionsCount() {
        return this.transactions.values().stream().mapToInt( List::size ).sum();
    }
}
