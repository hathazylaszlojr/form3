package com.form3.hometest.domain;

import com.form3.hometest.entity.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargeVO {
    
    private float amount;
    private Currency currency;
    
}
