package com.roya.showman.apis.dao;

import com.roya.showman.apis.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDao extends JpaRepository<TicketEntity, String> {
}
