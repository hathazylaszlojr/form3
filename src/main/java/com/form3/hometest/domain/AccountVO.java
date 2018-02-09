package com.form3.hometest.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountVO {

    private int accountNumber;
    private String accountName;
    private String accountNumberCode;
    private int accountType;
    private String address;
    private int bankId;
    private String bankIdCode;
    private String name;
    
}
