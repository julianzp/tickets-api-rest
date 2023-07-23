package com.doublev.tickets.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@Entity
@Table(name = "tickets")
@ToString @EqualsAndHashCode
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private long id;

    @Getter @Setter @Column(name = "usuario")
    private String usuario;

    @Getter @Setter @Column(name = "fechacreacion")
    private LocalDate  fechaCreacion;

    @Getter @Setter @Column(name = "fechaactualizacion")
    private LocalDate fechaActualizacion;

    @Getter @Setter @Column(name = "estatus")
    private String estatus;

}
