package com.example.createticket.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;



@Data
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTicket;

    @Column (length = 500)
    private String asunto;

    private int categoria;
    private String usuarioEmisor;
    private String emailEmisor;

    @Column(length = 500)
    private String descripcionTicket;

    private LocalDateTime fechaEmision;
    private int estadoTicket;
    private LocalDateTime fechaRespuesta;
    private String usuarioRecepto;

    @Column(length = 500)
    private String respuesta;

}
