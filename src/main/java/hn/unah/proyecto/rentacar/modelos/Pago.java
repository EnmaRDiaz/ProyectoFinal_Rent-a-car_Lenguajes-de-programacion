package hn.unah.proyecto.rentacar.modelos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="pago")
@Data
public class Pago {

    @Id
    @Column(name="idpago")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idPago;

    @Column(name="estadoPago")
    private boolean estadoPago;

    @Column(name="fechapago")
    private LocalDate fechaPago;

    private double monto;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="idalquiler")
    private Alquiler alquiler; 
}
