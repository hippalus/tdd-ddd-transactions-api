package com.transactions.rest.portweb.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionSearchResponse {
    private Collection<TransactionResponse> transactions=new ArrayList<>();
}
