package com.roya.showman.apis.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tickets")
public class TicketEntity {
    @Id
    @Column(name = "ticket_id", unique = true, nullable = false)
    private String ticketId;

    @Column(name = "price", nullable = false)
    private double price;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_number", referencedColumnName = "seat_number ", nullable = false)
    private SeatEntity seatEntity;

}
