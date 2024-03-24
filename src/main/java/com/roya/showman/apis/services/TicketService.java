package com.roya.showman.apis.services;

import com.roya.showman.apis.dtos.SeatDto;
import com.roya.showman.apis.dtos.TicketDto;

import java.util.List;

public interface TicketService {
    List<SeatDto> viewAvailableSeats();
    List<TicketDto> buyTicket(Integer count, String seatCode);
    String cancelTicket(String ticketId);
}
