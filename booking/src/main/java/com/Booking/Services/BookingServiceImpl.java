package com.Booking.Services;

import com.Booking.Dao.BookingDao;
import com.Booking.Dto.PaymentDTO;
import com.Booking.entities.BookingInfoEntity;
import com.Booking.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingServiceImpl implements BookingService
{
    @Autowired
    BookingDao bookingDao;

    @Override
    public BookingInfoEntity addBooking(BookingInfoEntity bookingInfoEntity) throws Exception
    {
        calculateRoomPrice(bookingInfoEntity);
        getRandomNumbers(bookingInfoEntity);
        getCurrentDate(bookingInfoEntity);
        BookingInfoEntity savedBookingInfoEntity = bookingDao.save(bookingInfoEntity);
        return savedBookingInfoEntity;
    }

    public void calculateRoomPrice(BookingInfoEntity bookingInfoEntity) throws Exception
    {
        Date from = bookingInfoEntity.getFromDate();
        Date to = bookingInfoEntity.getToDate();
        long diff = to.getTime() - from.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);

        long roomPrice = 1000* bookingInfoEntity.getNoOfRooms()*(diffDays);
        bookingInfoEntity.setRoomPrice((int)roomPrice);
    }

    @Override
    public ArrayList<String> getRandomNumbers(BookingInfoEntity bookingInfoEntity){
        Random rand = new Random();
        int upperBound = 100;
        int count = bookingInfoEntity.getNoOfRooms();
        ArrayList<String>numberList = new ArrayList<String>();

        for (int i=0; i<count; i++){
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }
        bookingInfoEntity.setRoomNumbers(String.join(", ", numberList));
        return numberList;
    }
    @Override
    public void getCurrentDate(BookingInfoEntity bookingInfoEntity){
        Date date = Calendar.getInstance().getTime();
        bookingInfoEntity.setBookedOn(date);
    }

    @Override
    public BookingInfoEntity updateTransactionId(int bookingId, int transactionId)
    {
        BookingInfoEntity bookingInfoEntity = bookingDao.getById(bookingId);
        bookingInfoEntity.setTransactionId(transactionId);
        return bookingDao.save(bookingInfoEntity);
    }

    @Override
    public void findBookingId(Integer bookingId)
    {
        BookingInfoEntity data = bookingDao.findById(bookingId).orElseThrow(
                ()-> new RecordNotFoundException("Invalid Booking Id")
        );
    }

    @Override
    public void checkPaymentMode(PaymentDTO data)
    {
        if(!(data.getPaymentMode().equals("CARD") || data.getPaymentMode().equals("UPI"))){
            throw new RecordNotFoundException("Invalid mode of payment");
        }
    }
}
