package com.example.createticket.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.createticket.model.Ticket;
import com.example.createticket.repository.TicketRepository;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket registrarTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> listarTickets() {
        return ticketRepository.findAll();
    }
    
    //PARA BUSCAR POR ID
    
  
    public Optional<Ticket> listarTicketPorId(int idTicket) {
        return ticketRepository.findById(idTicket);
    }

    public Ticket actualizarRespuesta(int idTicket, String respuesta, String usuarioRecepto) {
        // Buscamos el ticket por ID
        Optional<Ticket> optionalTicket = ticketRepository.findById(idTicket);
        
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setRespuesta(respuesta); // Actualiza la respuesta
            ticket.setFechaRespuesta(LocalDateTime.now()); // Establece la fecha de respuesta
            ticket.setUsuarioRecepto(usuarioRecepto); // Establece el usuario receptor
            ticket.setEstadoTicket(2); // Actualiza el estado a 2
            return ticketRepository.save(ticket); // Guarda el ticket actualizado
        } else {
            throw new RuntimeException("Ticket no encontrado con id: " + idTicket); // Manejo de excepciones
        }
    }
}
