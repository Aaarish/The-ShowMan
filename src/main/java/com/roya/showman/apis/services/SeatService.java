package com.roya.showman.apis.services;

import com.roya.showman.apis.dtos.SeatDto;

public interface SeatService {

    void addSeat(long seatId, String seatTier);
    SeatDto getSeatBySeatNumber(String seatNumber);
    void addAllSeats();
}
