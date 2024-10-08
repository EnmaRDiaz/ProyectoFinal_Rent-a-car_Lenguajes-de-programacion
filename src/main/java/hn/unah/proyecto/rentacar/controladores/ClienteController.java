package hn.unah.proyecto.rentacar.controladores;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.proyecto.rentacar.dtos.AlquilerDTO;
import hn.unah.proyecto.rentacar.modelos.Alquiler;
import hn.unah.proyecto.rentacar.modelos.Cliente;
import hn.unah.proyecto.rentacar.modelos.EEV;
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

    @PostMapping("/crear/alquiler/")
    public Cliente crearAlquiler(@RequestParam int idCliente, @RequestParam int vin, @RequestBody Alquiler nvoAlquiler) {
        return this.clienteServicio.crearAlquiler(idCliente, vin, nvoAlquiler);
    }
    
    @PostMapping("/registrar/cliente")
    public Cliente registrarCliente(@RequestBody Cliente nvoCliente) {
        return this.clienteServicio.registrarCliente(nvoCliente); 
    }

    @PutMapping("/fin/alquiler/")
    public Cliente finAlquiler(@RequestParam int idCliente, @RequestParam String ciudadEntrega, @RequestBody EEV nvoEev) {
        return this.clienteServicio.finAlquiler(idCliente, ciudadEntrega, nvoEev);
    }

    @GetMapping("/registro/venta")
    public List<AlquilerDTO> registroDeVentas(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return this.clienteServicio.registroEntreFechas(fechaInicio, fechaFin);
    }
    

    
}
