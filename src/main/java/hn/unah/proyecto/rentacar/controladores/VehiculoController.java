package hn.unah.proyecto.rentacar.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.proyecto.rentacar.modelos.Mantenimiento;
import hn.unah.proyecto.rentacar.modelos.Vehiculo;
import hn.unah.proyecto.rentacar.servicios.VehiculoServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/rentacar/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoServicio vehiculoServicio;

    @GetMapping("/mostrar/vehiculos")
    public List<Vehiculo> mostraVehiculos() {
        return this.vehiculoServicio.mostrarVehiculos();
    }

    @PostMapping("/crear/vehiculo/{idCiudad}")
    public Vehiculo crearVehiculo(@RequestBody Vehiculo nvoVehiculo, @PathVariable int idCiudad) {
        return this.vehiculoServicio.crearVehiculo(nvoVehiculo, idCiudad);
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
}
