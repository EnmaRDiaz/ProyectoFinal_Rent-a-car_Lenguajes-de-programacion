package hn.unah.proyecto.rentacar.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.proyecto.rentacar.modelos.Ciudad;
import hn.unah.proyecto.rentacar.servicios.CiudadServicio;


@RestController
@RequestMapping("/ciudad")
public class CiudadController {
    @Autowired
    private CiudadServicio ciudadServicio;
    
    @GetMapping("/obtener/{idCiudad}")
    public Ciudad obtenerCiudad(@PathVariable int idCiudad) {
        return this.ciudadServicio.devolverCiudad(idCiudad);
    }
    
}
