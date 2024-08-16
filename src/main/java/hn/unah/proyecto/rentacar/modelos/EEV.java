package hn.unah.proyecto.rentacar.modelos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "eev")
@Data
public class EEV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idestadoentrega")
    private long idEstadoEntrega;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "vin", referencedColumnName = "vin")
    private Vehiculo vehiculoEev;

    @Column(name = "fechainspeccion")
    private LocalDate fechaInspeccion;

    @Column(name = "descripciondanio")
    private String descripcionDanio;

    @Column(name = "costoestimado")
    private double costoEstimado;

    @Column(name = "niveldanio")
    private String nivelDanio;
}
