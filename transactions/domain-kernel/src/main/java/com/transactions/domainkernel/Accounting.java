package com.transactions.domainkernel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static com.transactions.domainkernel.TransactionState.APPROVED;

public class Accounting {
    private Map<PurchasingAgent, List<Transaction>> agentTransactions = new HashMap<>();
    private Money limit;
    private final AtomicLong idGenerator = new AtomicLong( 0 );

    public Map<PurchasingAgent, List<Transaction>> getAgentTransactions() {
        return agentTransactions;
    }

    public Accounting(Money limit) {
        this.limit = limit;
    }

    public boolean save(Transaction transaction) {
        var agent = transaction.getAgent();
        var totalPriceOfApprovedTransactions = calculateTotalPriceOfAgentTransactions( agent );

        if ( totalPriceOfApprovedTransactions.plus( transaction.getPrice() ).isGreaterThan( this.limit ) ) {
            transaction.unApprove();
        } else {
            transaction.approve();
        }
        List<Transaction> transactions = agentTransactions.getOrDefault( agent, new ArrayList<>() );
        transactions.add( transaction );
        transaction.setId( idGenerator.addAndGet( 1 ) );

        agentTransactions.putIfAbsent( agent, transactions );

        return transaction.getState() == APPROVED;
    }

    public Money calculateTotalPriceOfAgentTransactions(PurchasingAgent agent) {
        var transactions = agentTransactions.getOrDefault( agent, new ArrayList<>() );
        return transactions.stream()
                .filter( transaction -> transaction.getState() == APPROVED )
                .map( Transaction::getPrice )
                .reduce( Money.of( 0d ), Money::plus );


    }

    public Map<PurchasingAgent, List<Transaction>> getTransactionsOf(PurchasingAgent agent) {
        var transaction = agentTransactions.getOrDefault( agent, new ArrayList<>() );
        var result = new HashMap<PurchasingAgent, List<Transaction>>();
        result.put( agent, transaction );
        return result;

    }
}
