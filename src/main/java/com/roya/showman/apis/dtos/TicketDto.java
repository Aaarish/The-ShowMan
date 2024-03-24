package com.roya.showman.apis.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDto {
    @JsonProperty("ticket_id")
    private String ticketId;
    private double price;
    @JsonProperty("seat")
    private SeatDto seatDto;

}
