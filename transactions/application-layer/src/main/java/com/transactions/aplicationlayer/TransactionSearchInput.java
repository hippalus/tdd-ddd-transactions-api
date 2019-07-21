package com.transactions.aplicationlayer;

import com.transactions.domainkernel.PurchasingAgent;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class TransactionSearchInput {

    private PurchasingAgent agentToSearch;

    public boolean hasAgentToSearch(){
        return Objects.nonNull(agentToSearch);
    }

}
