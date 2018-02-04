package com.form3.hometest.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class Payment {
    
    private final String type;
    private final UUID id;
    private final int version;
    private final UUID organisationId;
    
}
