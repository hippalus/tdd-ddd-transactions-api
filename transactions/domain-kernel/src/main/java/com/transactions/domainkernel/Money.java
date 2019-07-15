package com.transactions.domainkernel;

import java.util.Objects;

public class Money {
    private Double value;

    public Money(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return this.value;
    }

    public static Money of(Double value) {
        return new Money( value );
    }

    public Money plus(Money value) {
        return Money.of( this.value+value.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof Money) ) return false;
        Money money = (Money) o;
        return Objects.equals( getValue(), money.getValue() );
    }

    @Override
    public int hashCode() {
        return Objects.hash( getValue() );
    }

    public boolean isGreaterThan(Money money) {
    return this.value>money.getValue();
    }
}
