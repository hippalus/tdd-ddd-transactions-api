package com.transactions.aplicationlayer;

import com.transactions.domainkernel.Accounting;
import com.transactions.domainkernel.Money;
import com.transactions.domainkernel.Product;
import com.transactions.domainkernel.PurchasingAgent;

import java.lang.ref.PhantomReference;

public class TransactionSaveCommand {
    private Accounting accounting;
    public TransactionSaveCommand(Accounting accounting) {
        this.accounting=accounting;
    }

    public TransactionSaveOutput exec(TransactionSaveInput input) {
        var agent = PurchasingAgent.aNew().firstName( input.getFirstName() )
                .lastName( input.getLastName() )
                .email( input.getEmail() )
                .build();

        var product=Product.aNew().name( input.getProductName() )
                .price( Money.of(input.getProductPrice()) ).get();

        var transaction=agent.buy( product ).withCode( input.getTransactionCode() ).build();

        this.accounting.save( transaction );
        return new TransactionSaveOutput(transaction.getId(),transaction.getState());

    }
}
