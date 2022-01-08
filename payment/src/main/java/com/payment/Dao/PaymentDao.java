package com.payment.Dao;

import com.payment.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDao extends JpaRepository<Payment, Integer>
{
}
