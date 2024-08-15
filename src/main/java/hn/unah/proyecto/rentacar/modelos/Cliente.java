package hn.unah.proyecto.rentacar.modelos;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="cliente")
@Data
public class Cliente {
    
    @Id
    @Column(name="idcliente")
    private int idCliente;

    private String nombre;

    private String apellido;

    private String direccion;

    private String telefono;

    private String email;

    @Column(name="fecharegistro")
    private LocalDate fechaRegistro;

    @OneToMany(mappedBy="cliente", cascade=CascadeType.ALL)
    private List<Alquiler> alquiler;



}
