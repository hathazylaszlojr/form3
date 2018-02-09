package com.form3.hometest.domain;

import com.form3.hometest.entity.Currency;
import lombok.Data;

@Data
public class ForexDataVO {

    private String contractReference;
    private Float exchangeRate;
    private Float originalAmount;
    private Currency originalCurrency;
    
}
