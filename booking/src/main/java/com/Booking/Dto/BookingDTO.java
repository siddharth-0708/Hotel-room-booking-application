package com.Booking.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class BookingDTO
{
    private int bookingId;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fromDate;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date toDate;

    private String aadharNumber;

    private int noOfRooms;

    private String roomNumbers;

    private int roomPrice;

    private int transactionId;

    private Date bookedOn;

    public int getBookingId()
    {
        return bookingId;
    }

    public void setBookingId(int bookingId)
    {
        this.bookingId = bookingId;
    }

    public Date getFromDate()
    {
        return fromDate;
    }

    public void setFromDate(Date fromDate)
    {
        this.fromDate = fromDate;
    }

    public Date getToDate()
    {
        return toDate;
    }

    public void setToDate(Date toDate)
    {
        this.toDate = toDate;
    }

    public String getAadharNumber()
    {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber)
    {
        this.aadharNumber = aadharNumber;
    }

    public int getNoOfRooms()
    {
        return noOfRooms;
    }

    public void setNoOfRooms(int noOfRooms)
    {
        this.noOfRooms = noOfRooms;
    }

    public String getRoomNumbers()
    {
        return roomNumbers;
    }

    public void setRoomNumbers(String roomNumbers)
    {
        this.roomNumbers = roomNumbers;
    }

    public int getRoomPrice()
    {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice)
    {
        this.roomPrice = roomPrice;
    }

    public int getTransactionId()
    {
        return transactionId;
    }

    public void setTransactionId(int transactionId)
    {
        this.transactionId = transactionId;
    }

    public Date getBookedOn()
    {
        return bookedOn;
    }

    public void setBookedOn(Date bookedOn)
    {
        this.bookedOn = bookedOn;
    }

    @Override
    public String toString()
    {
        return "BookingDTO{" +
                "bookingId=" + bookingId +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", aadharNumber='" + aadharNumber + '\'' +
                ", noOfRooms=" + noOfRooms +
                ", roomNumbers='" + roomNumbers + '\'' +
                ", roomPrice=" + roomPrice +
                ", transactionId=" + transactionId +
                ", bookedOn=" + bookedOn +
                '}';
    }
}
