package com.transactions.domainkernel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Accounting {
    private Map<PurchasingAgent, List<Transaction>> agentTransactions = new HashMap<>();
    private Money limit;

    public Accounting(Money limit) {
        this.limit = limit;
    }

    public boolean save(Transaction transaction) {






        return true;
    }
}
