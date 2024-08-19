package hn.unah.proyecto.rentacar.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.proyecto.rentacar.dtos.CiudadDTO;
import hn.unah.proyecto.rentacar.modelos.Mantenimiento;
import hn.unah.proyecto.rentacar.modelos.Vehiculo;
import hn.unah.proyecto.rentacar.servicios.VehiculoServicio;




@RestController
@RequestMapping("/api/rentacar/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoServicio vehiculoServicio;

    @GetMapping("/mostrar/vehiculos")
    public List<Vehiculo> mostraVehiculos() {
        return this.vehiculoServicio.mostrarVehiculos();
    }

    @PostMapping("/crear/vehiculo/{nombre}")
    public Vehiculo crearVehiculo(@RequestBody Vehiculo nvoVehiculo, @PathVariable String nombre) {
        return this.vehiculoServicio.crearVehiculo(nvoVehiculo, nombre);
    }
    
    @PostMapping("/crear/mantenimiento/{vin}")
    public Mantenimiento crearMantenimiento(@PathVariable int vin, @RequestBody Mantenimiento nvoMantenimiento) {
        return this.vehiculoServicio.crearMantenimiento(vin, nvoMantenimiento);
    }
    
    @GetMapping("/buscar/marca/{marca}")
    public List<Vehiculo> buscarPorMarca(@PathVariable String marca) {
        return this.vehiculoServicio.buscarPorMarca(marca);
    }
    
    @GetMapping("/buscar/modelo/{modelo}")
    public List<Vehiculo> buscarPorModelo(@PathVariable String modelo) {
        return this.vehiculoServicio.buscarPorModelo(modelo);
    }

    @GetMapping("/buscar/disponibilidad/{disponibilidad}")
    public List<Vehiculo> buscarPorDisponibilidad(@PathVariable Boolean disponibilidad) {
        return this.vehiculoServicio.buscarPorDisponibilidad(disponibilidad);
    }

    @GetMapping("/buscar/id/{vin}")
    public CiudadDTO buscarPorDisponibilidad(@PathVariable int vin) {
        return this.vehiculoServicio.buscarPorIdCiudad(vin);
    }

    @GetMapping("/buscar/vehiculo/{vin}")
    public Vehiculo buscarPorId(@PathVariable int vin) {
        return this.vehiculoServicio.buscarPorID(vin);
    }
    
}
