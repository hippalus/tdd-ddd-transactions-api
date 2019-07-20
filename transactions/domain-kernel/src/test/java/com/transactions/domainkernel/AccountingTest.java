package com.transactions.domainkernel;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class AccountingTest {


    @Test
    void should_calc_total_price_of_transactions_of_an_agent() {

        var macBook = Product.aNew().name( "Mac Book" ).price( Money.of( 15000d ) ).get();
        var monster = Product.aNew().name( "Monster GTX" ).price( Money.of( 7000d ) ).get();
        var msi = Product.aNew().name( "MSI " ).price( Money.of( 9000d ) ).get();
        var hp = Product.aNew().name( "Monster GTX" ).price( Money.of( 6000d ) ).get();

        var Jone = PurchasingAgent.aNew().email( "jone.wick@gmail.com" ).firstName( "Jone" ).lastName( "Wick" ).build();

        var transaction = Jone.buy( macBook ).withCode("TR123").build();
        var transaction2 = Jone.buy( monster ).withCode( "TR456" ).build();
        var transaction3 = Jone.buy( msi ).withCode( "TR234" ).build();
        var transaction4 = Jone.buy( hp ).withCode( "TR453" ).build();

        var accounting = new Accounting( Money.of( 24000d ) );

        accounting.save( transaction);
        accounting.save( transaction2 );
        accounting.save( transaction3);
        accounting.save( transaction4);

        assertEquals( Money.of( 22000d ),accounting.calculateTotalPriceOfAgentTransactions(Jone) );


    }


    @Test
    public void should_unApprove_transaction_if_it_makes_total_price_higher_than_limit(){
        var accounting = new Accounting(Money.of(300d));

        var agent1 = PurchasingAgent.aNew().firstName("john").lastName("doe").email("john_doe@gmail.com").build();
        var trx1 = agent1.buy(Product.aNew().name("USB Disc").price(Money.of(100d)).get()).withCode("TR123").build();
        var trx2 = agent1.buy(Product.aNew().name("Flash Disc").price(Money.of(150d)).get()).withCode("TR124").build();
        var trx3 = agent1.buy(Product.aNew().name("Hard Disc").price(Money.of(60d)).get()).withCode("TR125").build();
        var trx4 = agent1.buy(Product.aNew().name("Hard Disc 7200").price(Money.of(50d)).get()).withCode("TR126").build();

        var isTrx1Approved = accounting.save(trx1);
        var isTrx2Approved = accounting.save(trx2);
        var isTrx3Approved = accounting.save(trx3);
        var isTrx4Approved = accounting.save(trx4);

        assertTrue(isTrx1Approved);
        assertTrue(isTrx2Approved);
        assertFalse(isTrx3Approved);
        assertTrue(isTrx4Approved);
    }

    @Test
    public void should_calc_total_price_of_approved_transactions_of_an_agent(){
        var accounting = new Accounting(Money.of(400d));

        var agent1 = PurchasingAgent.aNew().firstName("john").lastName("doe").email("john_doe@gmail.com").build();
        var trx1 = agent1.buy(Product.aNew().name("USB Disc").price(Money.of(100d)).get()).withCode("TR123").build();
        var trx2 = agent1.buy(Product.aNew().name("Flash Disc").price(Money.of(200d)).get()).withCode("TR124").build();
        var trx3 = agent1.buy(Product.aNew().name("Hard Disc").price(Money.of(400d)).get()).withCode("TR125").build();


        var agent2 = PurchasingAgent.aNew().firstName("mary").lastName("doe").email("john_doe@gmail.com").build();
        var trx4 = agent2.buy(Product.aNew().name("USB Disc").price(Money.of(300d)).get()).withCode("TR123").build();
        var trx5 = agent2.buy(Product.aNew().name("Flash Disc").price(Money.of(500d)).get()).withCode("TR124").build();
        var trx6 = agent2.buy(Product.aNew().name("Hard Disc").price(Money.of(800d)).get()).withCode("TR125").build();

        accounting.save(trx1);
        accounting.save(trx2);
        accounting.save(trx3);
        accounting.save(trx4);
        accounting.save(trx5);
        accounting.save(trx6);

        assertEquals(Money.of(300d), accounting.calculateTotalPriceOfAgentTransactions(agent1));
        assertEquals(Money.of(300d), accounting.calculateTotalPriceOfAgentTransactions(agent2));
    }

    @Test
    public void should_return_all_transactions() {
        var accounting = new Accounting(Money.of(400d));

        var agent1 = PurchasingAgent.aNew().firstName("john").lastName("doe").email("john_doe@gmail.com").build();
        var trx1 = agent1.buy(Product.aNew().name("USB Disc").price(Money.of(100d)).get()).withCode("TR123").build();
        var trx2 = agent1.buy(Product.aNew().name("Flash Disc").price(Money.of(200d)).get()).withCode("TR124").build();
        var trx3 = agent1.buy(Product.aNew().name("Hard Disc").price(Money.of(400d)).get()).withCode("TR125").build();


        var agent2 = PurchasingAgent.aNew().firstName("mary").lastName("doe").email("john_doe@gmail.com").build();
        var trx4 = agent2.buy(Product.aNew().name("USB Disc").price(Money.of(300d)).get()).withCode("TR123").build();
        var trx5 = agent2.buy(Product.aNew().name("Flash Disc").price(Money.of(500d)).get()).withCode("TR124").build();
        var trx6 = agent2.buy(Product.aNew().name("Hard Disc").price(Money.of(800d)).get()).withCode("TR125").build();

        accounting.save(trx1);
        accounting.save(trx2);
        accounting.save(trx3);
        accounting.save(trx4);
        accounting.save(trx5);
        accounting.save(trx6);

        Map<PurchasingAgent, List<Transaction>> transactions = accounting.getAgentTransactions();
        assertEquals(2, transactions.size());
        assertEquals(6, transactions.values().stream().mapToInt( List::size ).sum());
    }

    @Test
    public void should_return_transactions_of_agent() {
        var accounting = new Accounting(Money.of(400d));

        var agent1 = PurchasingAgent.aNew().firstName("john").lastName("doe").email("john_doe@gmail.com").build();
        var trx1 = agent1.buy(Product.aNew().name("USB Disc").price(Money.of(100d)).get()).withCode("TR123").build();
        var trx2 = agent1.buy(Product.aNew().name("Flash Disc").price(Money.of(200d)).get()).withCode("TR124").build();
        var trx3 = agent1.buy(Product.aNew().name("Hard Disc").price(Money.of(400d)).get()).withCode("TR125").build();


        var agent2 = PurchasingAgent.aNew().firstName("mary").lastName("doe").email("john_doe@gmail.com").build();
        var trx4 = agent2.buy(Product.aNew().name("USB Disc").price(Money.of(300d)).get()).withCode("TR123").build();
        var trx5 = agent2.buy(Product.aNew().name("Flash Disc").price(Money.of(500d)).get()).withCode("TR124").build();
        var trx6 = agent2.buy(Product.aNew().name("Hard Disc").price(Money.of(800d)).get()).withCode("TR125").build();

        accounting.save(trx1);
        accounting.save(trx2);
        accounting.save(trx3);
        accounting.save(trx4);
        accounting.save(trx5);
        accounting.save(trx6);

        Map<PurchasingAgent, List<Transaction>> transactions = accounting.getTransactionsOf(agent1);
        assertEquals(3, transactions.get(agent1).size());
    }
}
