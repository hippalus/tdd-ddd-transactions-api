package com.transactions.domainkernel;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TransactionTest {
    private String trCode="TR123";
    @Test
    void should_have_product_info_with_trx_code(){
        Product product= Product.aNew().name( "USB Disc" ).price(Money.of( 100d)).get();
        Transaction transaction=new Transaction(product,trCode);
        assertEquals( "USB Disc" ,transaction.getProductName());
        assertEquals( Money.of( 100d ),transaction.getProductPrice() );
        assertEquals( this.trCode,transaction.getCode());

    }
}
