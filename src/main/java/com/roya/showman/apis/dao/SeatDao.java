package com.roya.showman.apis.dao;

import com.roya.showman.apis.entities.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatDao extends JpaRepository<SeatEntity ,String> {
    Optional<SeatEntity> findBySeatNumber(String seatNumber);
}
