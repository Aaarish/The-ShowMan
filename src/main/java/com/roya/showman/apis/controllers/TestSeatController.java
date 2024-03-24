package com.roya.showman.apis.controllers;

import com.roya.showman.apis.entities.SeatEntity;
import com.roya.showman.apis.services.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
public class TestSeatController {
    private final SeatService seatService;

    @PostMapping
    public void addSeats() {
        seatService.addAllSeats();
    }

}
