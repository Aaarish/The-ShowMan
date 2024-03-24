package com.roya.showman.apis.services;

import com.roya.showman.apis.dao.SeatDao;
import com.roya.showman.apis.dao.TicketDao;
import com.roya.showman.apis.dtos.SeatDto;
import com.roya.showman.apis.dtos.TicketDto;
import com.roya.showman.apis.entities.SeatEntity;
import com.roya.showman.apis.entities.TicketEntity;
import com.roya.showman.constants.SeatTier;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.roya.showman.constants.SeatTier.*;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketServiceImpl implements TicketService {
    private final TicketDao ticketDao;
    private final SeatDao seatDao;
    private final ModelMapper modelMapper;

    @Override
    public List<SeatDto> viewAvailableSeats() {
        return getAvailableSeats().stream()
                .map(availableSeat -> modelMapper.map(availableSeat, SeatDto.class))
                .toList();
    }

    @Override
    public List<TicketDto> buyTicket(Integer count, String seatCode) {
        SeatTier seatTier = getSeatTier(seatCode);

        List<SeatEntity> availableSeatsOfTierX = getSeatsOfXTier(seatTier);

        return bookSeat(availableSeatsOfTierX, count);
    }

    @Override
    public String cancelTicket(String ticketId) {
        TicketEntity ticketEntity = ticketDao.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("No ticket with this id exists"));

        ticketEntity.getSeatEntity().setAvailable(true);
        ticketDao.delete(ticketEntity);

        return ticketEntity.getTicketId() + " is cancelled !";
    }

    private SeatTier getSeatTier(String seatCode) {
        return Arrays.stream(values()).distinct()
                .filter(t -> t.getSeatCode().equals(seatCode))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid seat tier !"));
    }

    private List<SeatEntity> getAvailableSeats() {
        return seatDao.findAll().stream()
                .filter(SeatEntity::isAvailable)
                .toList();
    }

    private List<SeatEntity> getSeatsOfXTier(SeatTier seatTier) {
        return getAvailableSeats().stream()
                .filter(seat -> seat.getSeatTier().equals(seatTier))
                .toList();
    }

    private List<TicketDto> bookSeat(List<SeatEntity> xTierSeats, int count) {
        if(count > xTierSeats.size()) throw new RuntimeException("Sorry seats are not available !");

//        book count no of x tier seats
        List<TicketDto> tickets = new ArrayList<>();

        for(int seatCount=0; seatCount<count; seatCount++) {
            SeatEntity seat = xTierSeats.get(seatCount);
            TicketEntity ticket = TicketEntity.builder()
                    .ticketId(UUID.randomUUID().toString())
                    .price(seat.getSeatTier().getPrice())
                    .seatEntity(seat)
                    .build();

            TicketEntity savedTicket = ticketDao.save(ticket);

            seat.setAvailable(false);
            seatDao.save(seat);

            tickets.add(mapEntityToDto(savedTicket));
        }
//            if(!seatDto.isAvailable()) throw new RuntimeExceptions("Not enough consecutive seats available in this tier. Please select seats ony by one !");

        return tickets;
    }

    private TicketDto mapEntityToDto(TicketEntity ticket) {
        return TicketDto.builder()
                .ticketId(ticket.getTicketId())
                .price(ticket.getPrice())
                .seatDto(mapEntityToDto(ticket.getSeatEntity()))
                .build();
    }

    private SeatDto mapEntityToDto(SeatEntity seat) {
        return SeatDto.builder()
                .seatNumber(seat.getSeatNumber())
                .seatTier(seat.getSeatTier())
                .isAvailable(seat.isAvailable())
                .build();
    }

}
