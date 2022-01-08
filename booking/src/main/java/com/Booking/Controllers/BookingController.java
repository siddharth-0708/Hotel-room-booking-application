package com.Booking.Controllers;

import com.Booking.Dto.BookingDTO;
import com.Booking.Dto.PaymentDTO;
import com.Booking.Services.BookingService;
import com.Booking.entities.BookingInfoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/hotel", consumes = MediaType.APPLICATION_JSON_VALUE)
public class BookingController
{
    @Value("${payment.url}")
    private String paymentUrl;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/booking")
    public ResponseEntity AddBooking(@RequestBody BookingDTO bookingDTO) throws Exception {

        BookingInfoEntity bookingInfoEntity = modelMapper.map(bookingDTO, BookingInfoEntity.class);

        BookingInfoEntity savedBookingInfoEntity = bookingService.addBooking(bookingInfoEntity);

        BookingDTO response = modelMapper.map(savedBookingInfoEntity, BookingDTO.class);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie","key="+ response.toString());

        bookingService.calculateRoomPrice(bookingInfoEntity);
        return new ResponseEntity(response, headers, HttpStatus.CREATED);
    }

    @PostMapping("/booking/{bookingId}/transaction")
    public ResponseEntity transactionBooking(@RequestBody PaymentDTO data, @PathVariable int bookingId)
    {
        bookingService.findBookingId(data.getBookingId());
        bookingService.checkPaymentMode(data);

        ResponseEntity<Integer> response =  restTemplate.postForEntity(paymentUrl,data,Integer.class);
        if(response.getStatusCode() == HttpStatus.CREATED){
            BookingInfoEntity bookingInfoEntity = bookingService.updateTransactionId(bookingId,response.getBody());
            BookingDTO responseToSend = modelMapper.map(bookingInfoEntity, BookingDTO.class);
            String message = "Booking confirmed for user with aadhaar number: "
                    + bookingInfoEntity.getAadharNumber()
                    +    "    |    "
                    + "Here are the booking details:    " + bookingInfoEntity.toString();
            System.out.println(message);
            return new ResponseEntity(responseToSend, HttpStatus.CREATED);
        }
        return new ResponseEntity("something went wrong", HttpStatus.BAD_GATEWAY);
    }
}
