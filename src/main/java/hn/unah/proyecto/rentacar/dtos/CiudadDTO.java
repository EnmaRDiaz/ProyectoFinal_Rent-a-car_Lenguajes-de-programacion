package hn.unah.proyecto.rentacar.dtos;

import hn.unah.proyecto.rentacar.modelos.Vehiculo;
import lombok.Data;

@Data
public class CiudadDTO {

    private Vehiculo vehiculo;

    private String nombre;
    
}
