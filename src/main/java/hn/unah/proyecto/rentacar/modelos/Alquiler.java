package hn.unah.proyecto.rentacar.modelos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="alquiler")
@Data
public class Alquiler {

    @Id
    @Column(name="idalquiler")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idAlquiler;
    
    @Column(name="fechainicio")
    private LocalDate fechaInicio;

    @Column(name="fechafin")
    private LocalDate fechaFin;

    @Column(name="costototal")
    private double costoTotal;

    @Column(name="ciudadorigen")
    private String ciudadOrigen;

    @Column(name="ciudadentrega")
    private String ciudadEntrega;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="idcliente")
    private Cliente cliente;

    @OneToOne(mappedBy="alquiler", cascade=CascadeType.ALL)
    private Pago pago;
    
    @ManyToOne
    @JoinColumn(name="vin")
    private Vehiculo vehiculo;
}
