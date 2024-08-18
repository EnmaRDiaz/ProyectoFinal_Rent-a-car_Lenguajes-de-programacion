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
@Table(name = "mantenimiento")
@Data
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmantenimiento")
    private int idMantenimiento;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "vin", referencedColumnName = "vin")
    private Vehiculo vehiculo;

    @Column(name = "fechamantenimiento")
    private LocalDate fechaMantenimiento;

    private String descripcion;

    private double costo;
}
