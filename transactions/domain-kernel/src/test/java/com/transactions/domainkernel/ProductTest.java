package com.transactions.domainkernel;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    public void should_have_name_and_price(){
        Product product= Product.aNew().name("USB Disc").price( 100 ).get();
        assertEquals( "USB Disc" ,product.getName());
        assertEquals( 100d,product.getPrice() );
    }


}
