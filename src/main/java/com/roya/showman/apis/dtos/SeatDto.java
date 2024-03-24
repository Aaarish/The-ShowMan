package com.roya.showman.apis.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.roya.showman.constants.SeatTier;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatDto {
    @JsonProperty("seat_number")
    private String seatNumber;
    @JsonProperty("is_available")
    private boolean isAvailable;
    @JsonProperty("tier")
    private SeatTier seatTier;
    @JsonProperty("ticket")
    @JsonIgnore
    private TicketDto ticketDto;

}
