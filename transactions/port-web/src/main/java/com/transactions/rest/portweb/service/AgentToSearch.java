package com.transactions.rest.portweb.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentToSearch {
    private String firstName;
    private String lastName;
    private String email;


}
