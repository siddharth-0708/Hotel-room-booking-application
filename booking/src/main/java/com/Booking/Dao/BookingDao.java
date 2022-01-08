package com.Booking.Dao;

import com.Booking.entities.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDao extends JpaRepository<BookingInfoEntity, Integer>
{
}
