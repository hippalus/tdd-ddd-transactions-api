package com.transactions.domainkernel;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Product {
    private final String name;
    private final Money price;


    public Product(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }
    public static  Builder aNew(){
        return new Builder();
    }

    public static class Builder {

        private String name;
        private Money price;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(Money price) {
            this.price = price;
            return this;

        }

        public Product get() {
            //todo Custom Exception
            if ( this.name == null ) throw new RuntimeException( "Product name is required" );
            if ( this.price == null) throw new RuntimeException( "Product price is required" );

            return new Product( this.name, this.price );
        }
    }
}
