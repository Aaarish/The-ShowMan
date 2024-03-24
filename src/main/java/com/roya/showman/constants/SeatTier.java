package com.roya.showman.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum SeatTier {
    DRESS_CIRCLE("DC", 100.00),
    UPPER_CIRCLE("UC", 150.00),
    BALCONY("B", 200.00),
    VIP_BOX("V", 300.00)
    ;

    private String seatCode;
    private double price;

}

