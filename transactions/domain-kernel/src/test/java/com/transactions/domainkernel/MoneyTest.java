package com.transactions.domainkernel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MoneyTest {


    @Test
    public void  should_hold_value(){
        var money=Money.of(100d);
        assertEquals( Double.valueOf(100d),money.getValue());
    }

    @Test
    public void should_plus_immutable(){
        var money=Money.of( 100d );
        var increasedMoney=money.plus(Money.of(50d));
        assertEquals( Double.valueOf( 150d),increasedMoney.getValue());
    }
    @Test
    public void  should_be_equal_other_instance_with_same_valued(){
        var money1=Money.of( 100d );
        var money2=Money.of( 100d );
        assertEquals( money1,money2 );
    }
    @Test
    public void should_be_comparable_valued(){
        var money1=Money.of( 100d );
        var money2=Money.of( 50d );
        assertTrue( money1.isGreaterThan(money2));
        assertFalse( money1.isGreaterThan( money2.plus(money2)) );
    }
}
