package com.form3.hometest.entity;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.hibernate.annotations.CascadeType.*;

@Entity
@Table(name = "PAYMENT")
@Data
public class Payment {
    
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;
    
    @Column(name = "ORGANISATION_ID", columnDefinition = "BINARY(16)")
    private UUID organisationId;

    @Column(name = "TYPE")
    private String type;
    
    @Column(name = "VERSION")
    private Integer version;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DEBTOR_PARTY_ACCOUNT_NUMBER_FK")
    private Account senderAccount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BENEFICIARY_ACCOUNT_NUMBER_FK")
    private Account receiverAccount;

    @Column(name = "SCHEME_PAYMENT_TYPE")
    private String schemePaymentType;
    
    @Column(name = "SCHEME_PAYMENT_SUB_TYPE")
    private String schemePaymentSubType;
    
    @Column(name = "REFERENCE")
    private String reference;
    
    @Column(name = "PAYMENT_TYPE")
    private String paymentType;
    
    @Column(name = "PAYMENT_SCHEME")
    private PaymentScheme paymentScheme;
    
    @Column(name = "PAYMENT_PURPOSE")
    private String paymentPurpose;
    
    @Column(name = "PAYMENT_ID")
    private String paymentId;
    
    @Column(name = "NUMERIC_REFERENCE")
    private String numericReference;

    @Column(name = "CURRENCY")
    private Currency currency;    
    
    @Column(name = "AMOUNT")
    private Float amount;    
    
    @Column(name = "END_TO_END_REFERENCE")
    private String endToEndReference;    
    
    @Column(name = "CI_BEARER_CODE")
    private String bearerCode;    
    
    @Column(name = "FX_CONTRACT_REFERENCE")
    private String fxContractReference;    
    
    @Column(name = "FX_EXCHANGE_RATE")
    private Float fxExchangeRate;

    @Column(name = "FX_ORIGINAL_AMOUNT")
    private Float fxOriginalAmount;

    @Column(name = "FX_ORIGINAL_CURRENCY")
    private Currency fxOriginalCurrency;

    @Column(name = "PROCESSING_DATE")
    private Date processingDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SPONSOR_ACCOUNT_NUMBER_FK")
    private Account sponsorAccount;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name= "PAYMENT_ID")
    private List<Charge> senderCharges;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RECEIVER_CHARGE_ID_FK")
    private Charge receiverCharge;

}
