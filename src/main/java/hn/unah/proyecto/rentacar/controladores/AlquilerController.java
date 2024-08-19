package hn.unah.proyecto.rentacar.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.proyecto.rentacar.dtos.AlquilerDTO;
import hn.unah.proyecto.rentacar.modelos.Alquiler;
import hn.unah.proyecto.rentacar.servicios.AlquilerServicio;



@RestController
@RequestMapping("/api/rentacar/alquiler")
public class AlquilerController {

    @Autowired
    private AlquilerServicio alquilerServicio;

    @GetMapping("/mostrar/todo")
    public List<AlquilerDTO> mostrarTodos() {
        return this.alquilerServicio.mostrarTodos();
    }

    @GetMapping("/buscar/id/{idAlquiler}")
    public Alquiler buscarPorId(@PathVariable int idAlquiler) {
        return this.alquilerServicio.buscarPorId(idAlquiler);
    }
    
    

}
