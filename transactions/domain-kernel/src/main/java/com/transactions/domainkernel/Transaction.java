package com.transactions.domainkernel;

public class Transaction {

    private Product product;
    private String code;
    public Transaction(Product product, String transactionCode) {
        this.product=product;
        this.code=transactionCode;

    }

    public String  getProductName() {
        return this.product.getName();
    }

    public Money getProductPrice() {
        return this.product.getPrice();
    }

    public String getCode() {
        return this.code;
    }

    public static class Builder{

        private Product product;
        private String code;

        public Builder product(Product product){
            this.product=product;
            return this;

        }
        public Builder code(String transactionCode) {
            this.code = transactionCode;
            return this;
        }

        public Transaction build(){
            if (code == null) {
                throw new PropertyRequiredException("Transaction", "code");
            } else
            if (product == null) {
                throw new PropertyRequiredException("Transaction", "product");
            }

            return new Transaction( product,code);
        }
    }
}
