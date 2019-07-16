package com.transactions.domainkernel;


import org.junit.jupiter.api.Test;

import static com.transactions.domainkernel.TransactionState.*;
import static org.junit.jupiter.api.Assertions.*;
public class TransactionTest {
    private String trCode="TR123";
    private Product product= Product.aNew().name( "USB Disc" ).price(Money.of( 100d)).get();
    Transaction transaction=Transaction.aNew().product( product ).code( trCode ).build();
    @Test
    void should_have_product_info_with_trx_code(){

        assertEquals( "USB Disc" ,transaction.getProductName());
        assertEquals( Money.of( 100d ),transaction.getProductPrice() );
        assertEquals( this.trCode,transaction.getCode());
    }
    @Test
    void should_throw_PropertyRequiredException_if_no_trCode_specified(){

        Exception ex=assertThrows( PropertyRequiredException.class,()-> Transaction.aNew().product( product ).build());
        assertEquals( "Property code of model Transaction must not be null !",ex.getMessage() );
    }
    @Test
    void should_throw_PropertyRequiredException_if_no_product_specified(){

        Exception ex=assertThrows( PropertyRequiredException.class,()-> Transaction.aNew().code( trCode ).build());
        assertEquals( "Property product of model Transaction must not be null !",ex.getMessage() );
    }
    @Test
    void should_have_state_as_WAITING_APPROVE_by_default(){
        assertEquals( WAITING_APPROVE,transaction.getState());
    }
    @Test
    void should_be_approved(){
        transaction.approve();
        assertEquals( APPROVED,transaction.getState());
    }
    @Test
    void should_be_unapproved(){
        transaction.unApprove();
        assertEquals( UNAPPROVED,transaction.getState());
    }
    @Test
    void should_not_be_unapproved_if_already_approved(){
        transaction.approve();
        Exception ex=assertThrows( IllegalOperationException.class,() -> transaction.unApprove() );
        assertEquals( "Already unapproved transaction can't be approved!",ex.getMessage() );
    }

    @Test
    void should_not_be_approved_if_already_unapproved(){
        transaction.unApprove();
        Exception ex=assertThrows( IllegalOperationException.class,() -> transaction.approve() );
        assertEquals(  "Already approved transaction can't be unapproved!",ex.getMessage() );
    }




}
