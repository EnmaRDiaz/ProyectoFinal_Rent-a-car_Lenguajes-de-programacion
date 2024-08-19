package hn.unah.proyecto.rentacar.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.rentacar.dtos.AlquilerDTO;
import hn.unah.proyecto.rentacar.modelos.Alquiler;
import hn.unah.proyecto.rentacar.repositorios.AlquilerRepositorio;

@Service
public class AlquilerServicio {
    
    @Autowired
    private AlquilerRepositorio alquilerRepositorio;

    public List<AlquilerDTO> mostrarTodos(){
        List<Alquiler> alquilerTodo= this.alquilerRepositorio.findAll();
        List<AlquilerDTO> alquilerDTOs = new ArrayList<>();
        for (Alquiler alquiler: alquilerTodo) {
            AlquilerDTO alquilerDTO = new AlquilerDTO();
            alquilerDTO.setAlquiler(alquiler);
            alquilerDTO.setIdCliente(this.alquilerRepositorio.findidClienteByidAlquiler(alquiler.getIdAlquiler()));
            alquilerDTO.setVehiculo(alquiler.getVehiculo());
            alquilerDTOs.add(alquilerDTO);
        }
        return alquilerDTOs;
    }

    public Alquiler buscarPorId(int idAlquiler){
        return this.alquilerRepositorio.findById(idAlquiler).get();
    }
}
