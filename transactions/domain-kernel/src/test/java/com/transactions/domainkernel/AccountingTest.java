package com.transactions.domainkernel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class AccountingTest {
    @Test
    void should_calc_total_price_of_transactions_of_an_agent(){
        Accounting accounting=new Accounting(Money.of( 24000d ));

        PurchasingAgent Jone=PurchasingAgent.aNew().email( "jone.wick@gmail.com" ).firstName( "Jone" ).lastName( "Wick" ).build();
        Product macBook = Product.aNew().name( "Mac Book" ).price(Money.of( 15000d )).get();
        Product monster= Product.aNew().name( "Monster GTX" ).price( Money.of( 7000d ) ).get();

        Transaction transaction=Jone.buy( macBook );
        transaction.setCode( "TR123" );

        Transaction transaction1=Jone.buy( monster);
        transaction.setCode( "TR124" );

       assertTrue(accounting.save(transaction));


       // assertEquals();



    }
}
