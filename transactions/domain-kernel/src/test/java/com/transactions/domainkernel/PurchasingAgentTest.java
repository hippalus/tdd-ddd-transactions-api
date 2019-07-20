
package com.transactions.domainkernel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PurchasingAgentTest {

    private  String firstName="John";
    private String lastName="Wick";
    private  String email="john.wick@gmail.com";
    private PurchasingAgent agent= PurchasingAgent.aNew().firstName( firstName ).lastName( lastName).email( email ).build();

    @Test
     void  should_have_fullName(){
        assertEquals( "John Wick",this.agent.getFullName() );
    }
    @Test
     void  should_have_email(){

        assertEquals( email,this.agent.getEmail() );
    }

    @Test
     void  should_throw_ValidationException_when_no_email_specified(){
        var exception=assertThrows( RuntimeException.class,()->PurchasingAgent.aNew().firstName( firstName ).lastName( lastName).build() );
        assertEquals( "Property email of model Purchasing Agent must not be null !",exception.getMessage() );
    }
    @Test
    void  should_throw_ValidationException_when_no_firstName_specified(){
        var exception=assertThrows( RuntimeException.class,()->PurchasingAgent.aNew().email( email ).lastName( lastName).build() );
        assertEquals( "Property First Name of model Purchasing Agent must not be null !",exception.getMessage() );
    }
    @Test
    void  should_throw_ValidationException_when_no_lastName_specified(){
        Exception exception=assertThrows( RuntimeException.class,()->PurchasingAgent.aNew().email( email ).firstName( firstName).build() );
        assertEquals( "Property Last Name of model Purchasing Agent must not be null !",exception.getMessage() );
    }

    @Test
     void should_not_equal_to_agent_with_different_firstName() {
        var agent1 = PurchasingAgent.aNew().firstName("john").lastName("doe").email("john_doe@gmail.com").build();
        var agent2 = PurchasingAgent.aNew().firstName("mary").lastName("doe").email("john_doe@gmail.com").build();
        assertFalse(agent1.equals(agent2));
    }
    @Test
    public void should_equal_to_agent_with_same_info() {
        var agent1 = PurchasingAgent.aNew().firstName(firstName).lastName(lastName).email(email).build();
        var agent2 = PurchasingAgent.aNew().firstName(firstName).lastName(lastName).email(email).build();
        assertTrue(agent1.equals(agent2));
    }

    @Test
    public void should_not_equal_to_agent_with_different_lastName() {
        var agent1 = PurchasingAgent.aNew().firstName("john").lastName("doe").email("john_doe@gmail.com").build();
        var agent2 = PurchasingAgent.aNew().firstName("john").lastName("wick").email("john_doe@gmail.com").build();
        assertFalse(agent1.equals(agent2));
    }

    @Test
    void should_not_equal_to_agent_with_different_email() {
        var agent1 = PurchasingAgent.aNew().firstName(firstName).lastName(lastName).email("john_wick@gmail.com").build();
        var agent2 = PurchasingAgent.aNew().firstName(firstName).lastName(lastName).email("john.doe@gmail.com").build();
        assertFalse(agent1.equals(agent2));
    }

    @Test
    void should_buy_product_then_generate_transaction(){
        var transaction=this.agent.buy(Product.aNew().name( "Hard Disc" ).price( Money.of( 300d )).get()).withCode( "TR123" ).build();
        assertEquals(Money.of( 300d ),transaction.getPrice());
    }



}
