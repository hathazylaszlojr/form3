package com.form3.hometest.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
@Data
public class Account {

    @Id
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "ACCOUNT_NAME")
    private String accountName;

    @Column(name = "ACCOUNT_NUMBER_CODE")
    private String accountNumberCode;

    @Column(name = "ACCOUNT_TYPE")
    private Integer accountType;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "BANK_ID")
    private Integer bankId;

    @Column(name = "BANK_ID_CODE")
    private String bankIdCode;

    @Column(name = "NAME")
    private String name;

}
