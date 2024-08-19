package hn.unah.proyecto.rentacar.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.rentacar.modelos.Ciudad;
import hn.unah.proyecto.rentacar.repositorios.ciudadRepositorio;

@Service
public class CiudadServicio {
    
    @Autowired
    private ciudadRepositorio ciudadRepositorio;

    public Ciudad devolverCiudad(int idCiudad){
        return this.ciudadRepositorio.findById(idCiudad).get();
    }
}
