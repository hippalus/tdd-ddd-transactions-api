package com.transactions.aplicationlayer;

import com.transactions.domainkernel.Accounting;
import com.transactions.domainkernel.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.transactions.domainkernel.TransactionState.APPROVED;
import static com.transactions.domainkernel.TransactionState.UNAPPROVED;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionSaveCommandTest {
    @Test
    void should_save_transaction(){
        var input =new TransactionSaveInput();
        input.setFirstName("John");
        input.setLastName("Wick");
        input.setEmail("john.wick@gmail.com");
        input.setTransactionCode("TR123");
        input.setProductPrice(123d);
        input.setProductName("IPhone X");

        var accounting=new Accounting( Money.of( 100d ));
        var saveCommand =new TransactionSaveCommand(accounting);
        var output=saveCommand.exec(input);

        Assertions.assertEquals( Long.valueOf( 1 ),output.getTransactionId());


    }


    @Test
    public void should_save_approved_transaction() {
        var accounting = new Accounting(Money.of(300d));
        var saveCommand = new TransactionSaveCommand(accounting);

        var input1 = new TransactionSaveInput("John","Doe","john.doe@gmail.com","TR123",100d,"USB Disc");
        var input2 = new TransactionSaveInput("John","Doe","john.doe@gmail.com","TR124",150d,"Flash Disc");
        var input3 = new TransactionSaveInput("John","Doe","john.doe@gmail.com","TR125",60d,"Hard Disc");
        var input4 = new TransactionSaveInput("John","Doe","john.doe@gmail.com","TR126",50d,"Hard Disc 7200");

        var output1 = saveCommand.exec(input1);
        var output2 = saveCommand.exec(input2);
        var output3 = saveCommand.exec(input3);
        var output4 = saveCommand.exec(input4);

        assertEquals(APPROVED, output1.getTransactionState());
        assertEquals(APPROVED, output2.getTransactionState());
        assertEquals(UNAPPROVED, output3.getTransactionState());
        assertEquals(APPROVED, output4.getTransactionState());
    }
}
