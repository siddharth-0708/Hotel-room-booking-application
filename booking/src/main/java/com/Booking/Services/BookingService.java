package com.Booking.Services;

import com.Booking.Dto.PaymentDTO;
import com.Booking.entities.BookingInfoEntity;

import java.util.ArrayList;

public interface BookingService
{
    BookingInfoEntity addBooking(BookingInfoEntity bookingInfoEntity) throws Exception;
    void calculateRoomPrice(BookingInfoEntity bookingInfoEntity) throws Exception;
    ArrayList getRandomNumbers(BookingInfoEntity bookingInfoEntity) throws Exception;
    void getCurrentDate(BookingInfoEntity bookingInfoEntity) throws Exception;
    BookingInfoEntity updateTransactionId(int bookingId, int transactionId);
    void findBookingId(Integer bookingId);
    void checkPaymentMode(PaymentDTO data);

}
