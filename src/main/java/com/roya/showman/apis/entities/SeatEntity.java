package com.roya.showman.apis.entities;

import com.roya.showman.constants.SeatTier;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "seats")
public class SeatEntity {

    @Column(name = "seat_id", nullable = false)
    private Long seatId;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "seat_tier", nullable = false)
    private SeatTier seatTier;

    @Id
    @Column(name = "seat_number", unique = true, nullable = false)
    private String seatNumber;

}
