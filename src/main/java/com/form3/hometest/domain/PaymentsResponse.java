package com.form3.hometest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.form3.hometest.entity.Payment;
import lombok.AllArgsConstructor;


import java.util.List;

@AllArgsConstructor
public class PaymentsResponse {
    
    @JsonProperty("data")
    private List<PaymentVO> paymentVOS;
}
