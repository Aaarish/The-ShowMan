package com.roya.showman.apis.services;

import com.roya.showman.apis.dao.SeatDao;
import com.roya.showman.apis.dtos.SeatDto;
import com.roya.showman.apis.entities.SeatEntity;
import com.roya.showman.constants.SeatTier;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.roya.showman.constants.SeatTier.*;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private static final List<String> seatTiers = Arrays.asList(
            DRESS_CIRCLE.getSeatCode(),
            UPPER_CIRCLE.getSeatCode(),
            BALCONY.getSeatCode(),
            VIP_BOX.getSeatCode()
    );

    private final SeatDao seatDao;
    private final ModelMapper modelMapper;

    @Override
    public void addSeat(long seatId, String seatTier) {
        SeatTier tier = Arrays.stream(SeatTier.values())
                .filter(val -> val.getSeatCode().equals(seatTier))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid tier !"));

        SeatEntity seat = SeatEntity.builder()
                .seatId(seatId)
                .seatTier(tier)
                .seatNumber(seatTier + seatId)
                .isAvailable(true)
                .build();

        seatDao.save(seat);
    }

    @Override
    public SeatDto getSeatBySeatNumber(String seatNumber) {
        return modelMapper.map(seatDao.findBySeatNumber(seatNumber)
                .orElseThrow(() -> new RuntimeException("No seat with such seat number")), SeatDto.class);
    }


    // TODO: 3/25/2024 -> make this method to called from a scheduled cron job

    @Override
    public void addAllSeats() {
        for(String seatTier : seatTiers) {
            for(int i=0; i<25; i++) {
                addSeat(i+1, seatTier);
            }
        }
    }

}
