package com.transactions.rest.portweb;

import com.transactions.aplicationlayer.TransactionSaveCommand;
import com.transactions.aplicationlayer.TransactionSearchCommand;
import com.transactions.domainkernel.Accounting;
import com.transactions.domainkernel.Money;
import com.transactions.domainkernel.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PortWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortWebApplication.class, args);
    }

    @Bean
    public  Accounting getAccounting(){
        return new Accounting(Money.of(300d));
    }
    @Bean
    public TransactionSaveCommand getSaveCommand(Accounting accounting){
        return new TransactionSaveCommand(accounting);

    }

    @Bean
    public TransactionSearchCommand getTransactionSearchCommand(Accounting accounting){

        return new TransactionSearchCommand(accounting);
    }
}
