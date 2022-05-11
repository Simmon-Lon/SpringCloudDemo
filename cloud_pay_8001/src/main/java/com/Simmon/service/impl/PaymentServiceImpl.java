package com.Simmon.service.impl;

import com.Simmon.entity.Payment;
import com.Simmon.repository.PaymentRepository;
import com.Simmon.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public int create(Payment payment) {
        return paymentRepository.create(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.getPaymentById(id);
    }
}
