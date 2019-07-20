package com.transactions.domainkernel;

import static com.transactions.domainkernel.TransactionState.*;

public class Transaction {
    private Long id;
    private final PurchasingAgent agent;
    private final Product product;
    private  String code;
    private TransactionState state = WAITING_APPROVE;

    public Transaction(PurchasingAgent agent, Product product, String transactionCode) {
        this.agent=agent;
        this.product = product;
        this.code = transactionCode;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public PurchasingAgent getAgent() {
        return agent;
    }

    public Product getProduct() {
        return product;
    }
    public Money getPrice() {
        return this.product.getPrice();
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
        private PurchasingAgent agent;

        public Builder agent(PurchasingAgent agent) {
            this.agent = agent;
            return this;
        }

        public Builder product(Product product) {
            this.product = product;
            return this;

        }

        public Builder withCode(String transactionCode) {
            this.code = transactionCode;
            return this;
        }

        public Transaction build() {
            if ( code == null ) {
                throw new PropertyRequiredException( "Transaction", "withCode" );
            } else if ( product == null ) {
                throw new PropertyRequiredException( "Transaction", "product" );
            }

            return new Transaction(agent, product, code );
        }

    }
}
