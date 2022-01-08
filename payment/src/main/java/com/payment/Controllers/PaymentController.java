package com.payment.Controllers;

import com.payment.Dto.PaymentDTO;
import com.payment.Services.PaymentService;
import com.payment.entities.Payment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController
{
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/transaction",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity transaction(@RequestBody PaymentDTO paymentDTO) throws Exception
    {
        Payment payment = modelMapper.map(paymentDTO, Payment.class);

        int createTransaction = paymentService.generateTransaction(payment);

        return new ResponseEntity(createTransaction, HttpStatus.CREATED);
    }
    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity getPaymentDetails(@PathVariable int transactionId) throws Exception
    {
        Payment payment = paymentService.getPaymentDetails(transactionId);

        PaymentDTO response = modelMapper.map(payment, PaymentDTO.class);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
