package com.form3.hometest.domain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import java.util.List;

@Data
public class ChargesInformationVO {
    
    private String bearerCode;
    private List<ChargeVO> senderCharges;
    
    @JsonUnwrapped(prefix = "receiver_charges_")
    private ChargeVO receiverCharge;
    
}
