
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
        Exception exception=assertThrows( RuntimeException.class,()->PurchasingAgent.aNew().firstName( firstName ).lastName( lastName).build() );
        assertEquals( "Email is required",exception.getMessage() );
    }
    @Test
    void  should_throw_ValidationException_when_no_firstName_specified(){
        Exception exception=assertThrows( RuntimeException.class,()->PurchasingAgent.aNew().email( email ).lastName( lastName).build() );
        assertEquals( "First Name is required ",exception.getMessage() );
    }
    @Test
    void  should_throw_ValidationException_when_no_lastName_specified(){
        Exception exception=assertThrows( RuntimeException.class,()->PurchasingAgent.aNew().email( email ).firstName( firstName).build() );
        assertEquals( "Last Name is required ",exception.getMessage() );
    }

    @Test
     void should_not_equal_to_agent_with_different_firstName() {
        PurchasingAgent agent1 = PurchasingAgent.aNew().firstName("john").lastName("doe").email("john_doe@gmail.com").build();
        PurchasingAgent agent2 = PurchasingAgent.aNew().firstName("mary").lastName("doe").email("john_doe@gmail.com").build();
        assertFalse(agent1.equals(agent2));
    }
    @Test
    public void should_equal_to_agent_with_same_info() {
        PurchasingAgent agent1 = PurchasingAgent.aNew().firstName(firstName).lastName(lastName).email(email).build();
        PurchasingAgent agent2 = PurchasingAgent.aNew().firstName(firstName).lastName(lastName).email(email).build();
        assertTrue(agent1.equals(agent2));
    }

    @Test
    public void should_not_equal_to_agent_with_different_lastName() {
        PurchasingAgent agent1 = PurchasingAgent.aNew().firstName("john").lastName("doe").email("john_doe@gmail.com").build();
        PurchasingAgent agent2 = PurchasingAgent.aNew().firstName("john").lastName("wick").email("john_doe@gmail.com").build();
        assertFalse(agent1.equals(agent2));
    }

    @Test
    void should_not_equal_to_agent_with_different_email() {
        PurchasingAgent agent1 = PurchasingAgent.aNew().firstName(firstName).lastName(lastName).email("john_wick@gmail.com").build();
        PurchasingAgent agent2 = PurchasingAgent.aNew().firstName(firstName).lastName(lastName).email("john.doe@gmail.com").build();
        assertFalse(agent1.equals(agent2));
    }



}
