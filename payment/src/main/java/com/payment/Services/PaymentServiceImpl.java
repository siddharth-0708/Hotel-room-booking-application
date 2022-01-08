package com.payment.Services;

import com.payment.Dao.PaymentDao;
import com.payment.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService
{
    @Autowired
    PaymentDao paymentDao;

    @Override
    public int generateTransaction(Payment payment) throws Exception
    {
        Payment savedPayment = paymentDao.save(payment);
        return savedPayment.getTransactionId();
    }

    @Override
    public Payment getPaymentDetails(int transactionID) throws Exception
    {
        Payment payment = paymentDao.getById(transactionID);
        return payment;
    }
}
