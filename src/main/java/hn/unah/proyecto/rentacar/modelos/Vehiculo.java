package hn.unah.proyecto.rentacar.modelos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "vehiculo")
@Data
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vin;

    private String marca;

    private String modelo;

    private long anio;

    private String color;

    private boolean disponibilidad;

    @Column(name = "preciodiario")
    private double precioDiario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idciudad", referencedColumnName = "idciudad")
    private Ciudad ciudad;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private List<EEV> eevs;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private List<Mantenimiento> mantenimientos;
}
