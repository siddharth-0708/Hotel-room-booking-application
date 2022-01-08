package com.payment.Services;

import com.payment.entities.Payment;

public interface PaymentService
{
    int generateTransaction(Payment payment) throws Exception;
    Payment getPaymentDetails(int transactionID) throws Exception;
}
