package com.transactions.domainkernel;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    public void should_have_name_and_price(){
        var product= Product.aNew().name("USB Disc").price( Money.of( 100d ) ).get();
        assertEquals( "USB Disc" ,product.getName());
        assertEquals( Money.of( 100d ),product.getPrice() );
    }


}
