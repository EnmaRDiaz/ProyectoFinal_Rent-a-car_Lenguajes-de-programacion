package hn.unah.proyecto.rentacar.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.proyecto.rentacar.modelos.Alquiler;
import hn.unah.proyecto.rentacar.modelos.Cliente;
import hn.unah.proyecto.rentacar.servicios.ClienteServicio;




@RestController
@RequestMapping("/api/rentacar/clientes")
public class ClienteController {

    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("/mostrar/todos/clientes")
    public List<Cliente> mostrarClientes() {
        return this.clienteServicio.mostrarClientes();
    }

    @GetMapping("/buscar/cliente/{idCliente}")
    public Cliente buscarCliente(@PathVariable int idCliente) {
        return this.clienteServicio.buscarPoridCliente(idCliente);
    }

    @PostMapping("/crear/renta/{idCliente}")
    public Cliente crearAlquiler(@PathVariable int idCliente, @RequestBody Alquiler nvoAlquiler) {
        return this.clienteServicio.crearAlquiler(idCliente, nvoAlquiler);
    }
    
    
    
    
    @PostMapping("/registrar/cliente")
    public Cliente registrarCliente(@RequestBody Cliente nvoCliente) {
        return this.clienteServicio.registrarCliente(nvoCliente); 
    }

    
}
