package hn.unah.proyecto.rentacar.modelos;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ciudad")
@Data
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idciudad")
    private long idCiudad;

    private String nombre;

    private String estado;

    private String pais;

    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL)
    private List<Vehiculo> vehiculos;
}
