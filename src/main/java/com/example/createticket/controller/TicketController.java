package com.example.createticket.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.createticket.model.Ticket;
import com.example.createticket.service.TicketService;


@CrossOrigin(origins = "http://localhost:3000") // Permitir CORS para este controlador
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarTicket(@RequestBody Ticket ticket) {
        // Seteamos la fecha de emisión antes de guardar
        ticket.setFechaEmision(LocalDateTime.now());

        // Guardamos el ticket
        Ticket nuevoTicket = ticketService.registrarTicket(ticket);

        // Preparamos la respuesta con el id, asunto y fecha de creación
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("idTicket", nuevoTicket.getIdTicket());
        respuesta.put("asunto", nuevoTicket.getAsunto());
        respuesta.put("fechaEmision", nuevoTicket.getFechaEmision());

        // Devolvemos la respuesta
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{idTicket}/respuesta")
    public ResponseEntity<Map<String, Object>> actualizarRespuesta(
            @PathVariable int idTicket, 
            @RequestBody Map<String, String> request) { // Cambiado a Map para recibir múltiples campos
            
        String respuesta = request.get("respuesta");
        String usuarioReceptor = request.get("usuarioReceptor");
        
        Ticket ticketActualizado = ticketService.actualizarRespuesta(idTicket, respuesta, usuarioReceptor);
        
        // Preparamos la respuesta
        Map<String, Object> respuestaMap = new HashMap<>();
        respuestaMap.put("idTicket", ticketActualizado.getIdTicket());
        respuestaMap.put("respuesta", ticketActualizado.getRespuesta());
        respuestaMap.put("fechaRespuesta", ticketActualizado.getFechaRespuesta());
        
        // Devolvemos la respuesta
        return ResponseEntity.ok(respuestaMap);
    }

    @GetMapping 
    public List<Ticket> listarTickets() {
        return ticketService.listarTickets();
    }
    
    
    @GetMapping("/{idTicket}")
    public ResponseEntity<Map<String, Object>> obtenerTicketPorId(@PathVariable int idTicket) {
        Optional<Ticket> optionalTicket = ticketService.listarTicketPorId(idTicket);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            // Preparamos los datos del ticket como respuesta
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("idTicket", ticket.getIdTicket());
            respuesta.put("asunto", ticket.getAsunto());
            respuesta.put("descripcionTicket", ticket.getDescripcionTicket());
            respuesta.put("estadoTicket", ticket.getEstadoTicket());
            respuesta.put("fechaEmision", ticket.getFechaEmision());
            respuesta.put("categoria", ticket.getCategoria());
            respuesta.put("usuarioEmisor", ticket.getUsuarioEmisor());
            respuesta.put("emailEmisor", ticket.getEmailEmisor());
            respuesta.put("fechaRespuesta", ticket.getFechaRespuesta());
            respuesta.put("usuarioRecepto", ticket.getUsuarioRecepto());
            respuesta.put("respuesta", ticket.getRespuesta());

            return ResponseEntity.ok(respuesta);
        } else {
            // Si no se encuentra el ticket, devolvemos un 404
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Ticket no encontrado con id: " + idTicket);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
  
}
