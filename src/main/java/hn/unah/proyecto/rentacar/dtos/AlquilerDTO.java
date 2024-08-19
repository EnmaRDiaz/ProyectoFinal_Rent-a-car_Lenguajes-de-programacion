package hn.unah.proyecto.rentacar.dtos;

import hn.unah.proyecto.rentacar.modelos.Alquiler;
import hn.unah.proyecto.rentacar.modelos.Vehiculo;
import lombok.Data;

@Data
public class AlquilerDTO {
    
    private Alquiler alquiler;

    private int idCliente;

    private Vehiculo vehiculo;
}
