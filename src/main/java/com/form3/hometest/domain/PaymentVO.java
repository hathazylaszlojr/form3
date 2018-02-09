package com.form3.hometest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class PaymentVO {
    
    private String type;
    private UUID id;
    private int version;
    private UUID organisationId;
    private PaymentAttributesVO attributes;
    
}
