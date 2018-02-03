package com.form3.hometest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.UUID;

@Value
public class Payment {
    
    private String type;
    private UUID id;
    private int version;
    
    @JsonProperty("organisation_id")
    private UUID organisationId;
    
}
