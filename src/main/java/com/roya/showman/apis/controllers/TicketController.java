package com.roya.showman.apis.controllers;

import com.roya.showman.apis.dtos.Response;
import com.roya.showman.apis.dtos.SeatDto;
import com.roya.showman.apis.dtos.TicketDto;
import com.roya.showman.constants.Status;
import com.roya.showman.apis.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<SeatDto>> viewAvailableSeats() {
        return ResponseEntity.status(HttpStatus.OK).body(ticketService.viewAvailableSeats());
    }

    @PostMapping
    public ResponseEntity<List<TicketDto>> buyTicket(@RequestParam(name = "count", required = false) Integer count, @RequestParam(name = "code", required = false) String seatCode) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.buyTicket(count, seatCode));
    }

    @PostMapping("/{ticketId}")
    public ResponseEntity<String> cancelTicket(@PathVariable String ticketId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ticketService.cancelTicket(ticketId));
    }

}
