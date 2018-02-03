package com.form3.hometest.controller;

import com.form3.hometest.domain.Payment;
import com.form3.hometest.domain.PaymentsResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentsController {
    
    @RequestMapping(method = RequestMethod.GET)
    public PaymentsResponse hello() {
        return new PaymentsResponse(Collections.singletonList(new Payment("Payment", null, 0, null)));
    }
    
}
