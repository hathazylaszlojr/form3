package com.form3.hometest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CHARGES")
public class Charge {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", unique = true)
    private Integer id;
    
    @Column(name = "AMOUNT")
    private Float amount;
    
    @Column(name = "CURRENCY")
    private Currency currency;

}
