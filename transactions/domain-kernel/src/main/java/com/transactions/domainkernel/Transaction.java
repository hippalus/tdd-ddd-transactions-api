package com.transactions.domainkernel;

import static com.transactions.domainkernel.TransactionState.*;

public class Transaction {

    private Product product;
    private String code;
    private TransactionState state = WAITING_APPROVE;

    public Transaction(Product product, String transactionCode) {
        this.product = product;
        this.code = transactionCode;

    }

    public String getProductName() {
        return this.product.getName();
    }

    public Money getProductPrice() {
        return this.product.getPrice();
    }

    public String getCode() {
        return this.code;
    }

    public static Builder aNew() {
        return new Builder();
    }

    public TransactionState getState() {
        return this.state;
    }

    public void approve() {
        if ( this.state == UNAPPROVED ) {
            throw new IllegalOperationException( "Already approved transaction can't be unapproved!" );

        }
        this.state = APPROVED;

    }

    public void unApprove() {
        if ( this.state == APPROVED ) {
            throw new IllegalOperationException( "Already unapproved transaction can't be approved!" );
        }
        this.state = UNAPPROVED;
    }

    public static class Builder {

        private Product product;
        private String code;

        public Builder product(Product product) {
            this.product = product;
            return this;

        }

        public Builder code(String transactionCode) {
            this.code = transactionCode;
            return this;
        }

        public Transaction build() {
            if ( code == null ) {
                throw new PropertyRequiredException( "Transaction", "code" );
            } else if ( product == null ) {
                throw new PropertyRequiredException( "Transaction", "product" );
            }

            return new Transaction( product, code );
        }
    }
}
