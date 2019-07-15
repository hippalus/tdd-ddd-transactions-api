package com.transactions.domainkernel;

import java.util.Objects;

public class PurchasingAgent {

    private final String firstName;
    private final String lastName;
    private final String email;


    public PurchasingAgent(String firstName, String lastName, String email) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {
        return String.format( "%s %s", this.firstName, this.lastName );
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public static Builder aNew() {
        return new Builder();
    }

    public static class Builder {

        private String firstName;
        private String lastName;
        private String email;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public PurchasingAgent build() {
            //todo custom exception
            if ( this.firstName == null ) {
                throw new RuntimeException( "First Name is required " );

            } else if ( this.lastName == null ) {
                throw new RuntimeException( "Last Name is required " );
            } else if ( this.email == null ) throw new RuntimeException( "Email is required" );
            return new PurchasingAgent( this.firstName, this.lastName, this.email );
        }


    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof PurchasingAgent) ) return false;
        PurchasingAgent that = (PurchasingAgent) o;
        return Objects.equals( getFirstName(), that.getFirstName() ) &&
                Objects.equals( getLastName(), that.getLastName() ) &&
                Objects.equals( getEmail(), that.getEmail() );
    }

    @Override
    public int hashCode() {
        return Objects.hash( getFirstName(), getLastName(), getEmail() );
    }
}
