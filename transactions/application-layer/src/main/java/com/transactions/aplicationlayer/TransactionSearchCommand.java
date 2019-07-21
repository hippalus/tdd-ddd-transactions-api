package com.transactions.aplicationlayer;

import com.transactions.domainkernel.Accounting;
import com.transactions.domainkernel.PurchasingAgent;
import com.transactions.domainkernel.Transaction;

import java.util.List;
import java.util.Map;

public class TransactionSearchCommand {
    private final Accounting accounting;

    public TransactionSearchCommand(Accounting accounting) {
        this.accounting = accounting;
    }

    public TransactionSearchOutput exec(TransactionSearchInput searchInput) {
        Map<PurchasingAgent, List<Transaction>> transactions;
        if ( searchInput.hasAgentToSearch() )
            transactions = accounting.getTransactionsOf( searchInput.getAgentToSearch() );
        else transactions = accounting.getAgentTransactions();
        return new TransactionSearchOutput( transactions );
    }
}
