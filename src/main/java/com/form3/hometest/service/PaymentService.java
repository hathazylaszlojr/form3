package com.form3.hometest.service;

import com.form3.hometest.entity.Payment;
import com.form3.hometest.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {
    
    private final PaymentRepository paymentRepository;
    
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    public Payment save(Payment payment) {
        return paymentRepository.savePayment(payment);
    }

    public boolean update(Payment payment) {
        if (paymentRepository.exists(payment.getId())) {
            paymentRepository.savePayment(payment);
            return true;
        }
        return false;
    }
    
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }

    public boolean delete(UUID id) {
        Payment payment = paymentRepository.getById(id);
        if (payment != null) {
            paymentRepository.delete(payment);
        }
        return payment != null;
    }

    public Payment getPaymentById(UUID id) {
        return paymentRepository.getById(id);
    }
}
