package hn.unah.proyecto.rentacar.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.rentacar.modelos.Cliente;
import hn.unah.proyecto.rentacar.repositorios.ClienteRepositorio;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public List<Cliente> mostrarClientes(){
        return this.clienteRepositorio.findAll();
    }

    public Cliente registrarCliente(Cliente nvoCliente){
        if(!comprobarExistencia(nvoCliente.getIdCliente())){
            
            return this.clienteRepositorio.save(nvoCliente); 
        }
        return null;
    }

    public Cliente buscarPoridCliente(int idCliente){
        return this.clienteRepositorio.findById(idCliente).get();
    }

    public boolean comprobarExistencia(int idCliente){
        return this.clienteRepositorio.existsById(idCliente);
    }    
}
