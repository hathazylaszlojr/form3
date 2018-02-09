package com.form3.hometest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.form3.hometest.domain.PaymentVO;
import com.form3.hometest.domain.PaymentsResponse;
import com.form3.hometest.entity.Account;
import com.form3.hometest.entity.Payment;
import com.form3.hometest.mapper.PaymentMapper;
import com.form3.hometest.mapper.PaymentVOMapper;
import com.form3.hometest.service.AccountService;
import com.form3.hometest.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {
    
    private final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    
    private final PaymentService paymentService;
    
    private final ObjectMapper objectMapper;
    
    private final PaymentMapper paymentMapper;
    
    private final PaymentVOMapper paymentVOMapper;
    
    @Autowired
    public PaymentController(PaymentService paymentService, ObjectMapper objectMapper, PaymentMapper paymentMapper, PaymentVOMapper paymentVOMapper) {
        this.paymentService = paymentService;
        this.objectMapper = objectMapper;
        this.paymentMapper = paymentMapper;
        this.paymentVOMapper = paymentVOMapper;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getPayments() {
        logger.info("Incoming GET all payments request...");
        List<PaymentVO> paymentVOS = paymentVOMapper.mapFromPayment(paymentService.getAllPayments());
        return new ResponseEntity<>(new PaymentsResponse(paymentVOS), HttpStatus.OK);
    }

    @RequestMapping(value = "{paymentId}", method = RequestMethod.GET)
    public ResponseEntity getPayment(@PathVariable("paymentId") String id) {
        logger.info("Incoming GET for one payment request: {}", id);
        PaymentVO paymentVO = paymentVOMapper.mapFromPayment(paymentService.getPaymentById(UUID.fromString(id)));
        return new ResponseEntity<>(new PaymentsResponse(Collections.singletonList(paymentVO)), HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addPayment(@RequestBody String paymentVoStr) {
        logger.info("New payment POSTed through the API...");
        
        PaymentVO paymentVO = null;

        try {
            paymentVO = objectMapper.readValue(paymentVoStr, PaymentVO.class);
        } catch (IOException e) {
            logger.error("Could not deserialize payment: {}", paymentVoStr, e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Payment payment = paymentMapper.mapToPayment(paymentVO);
        paymentService.save(payment);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "{paymentId}", method = RequestMethod.PUT)
    public ResponseEntity updatePayment(@PathVariable("paymentId") String paymentId, @RequestBody String paymentVoStr) {
        logger.info("Payment update submitted through the API for {}", paymentId);
        
        PaymentVO paymentVO = null;

        try {
            paymentVO = objectMapper.readValue(paymentVoStr, PaymentVO.class);
        } catch (IOException e) {
            logger.error("Could not deserialize payment: {}", paymentVoStr);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Payment payment = paymentMapper.mapToPayment(paymentVO);
        
        if (!paymentId.equals(payment.getId().toString())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        boolean paymentUpdated = paymentService.update(payment);
        if (paymentUpdated) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "{paymentId}", method = RequestMethod.DELETE)
    public ResponseEntity deletePayment(@PathVariable("paymentId") String paymentId) {
        logger.info("Payment delete submitted through the API for {}", paymentId);

        boolean paymentDeleted = paymentService.delete(UUID.fromString(paymentId));
        if (paymentDeleted) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
}
