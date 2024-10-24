package com.example.createticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.createticket.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
