package com.form3.hometest.domain;

import com.form3.hometest.entity.Account;
import com.form3.hometest.entity.Currency;
import com.form3.hometest.entity.PaymentScheme;
import lombok.Data;

import java.util.Date;

@Data
public class PaymentAttributesVO {

    private Account beneficiaryParty;
    private Account debtorParty;
    private String schemePaymentType;
    private String schemePaymentSubType;
    private String reference;
    private PaymentScheme paymentScheme;
    private ChargesInformationVO chargesInformation;
    private String paymentPurpose;
    private String paymentId;
    private String paymentType;
    private String numericReference;
    private Currency currency;
    private Float amount;
    private String endToEndReference;
    private ForexDataVO fx;
    private Date processingDate;
    private Account sponsorParty;
    
    
}
