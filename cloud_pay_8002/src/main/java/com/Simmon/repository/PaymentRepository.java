package com.Simmon.repository;

import com.Simmon.entity.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}
