package hn.unah.proyecto.rentacar.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.rentacar.modelos.Ciudad;
import hn.unah.proyecto.rentacar.modelos.Mantenimiento;
import hn.unah.proyecto.rentacar.modelos.Vehiculo;
import hn.unah.proyecto.rentacar.repositorios.CiudadRepositorio;
import hn.unah.proyecto.rentacar.repositorios.MantenimientoRepositorio;
import hn.unah.proyecto.rentacar.repositorios.VehiculoRepositorio;

@Service
public class VehiculoServicio {

    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;

    @Autowired
    private CiudadRepositorio ciudadRepositorio;

    @Autowired
    private MantenimientoRepositorio mantenimientoRepositorio;

    //MostrarVehiculos(): muestra todos los vehículos con el registro de mantenimiento.
    public List<Vehiculo> mostrarVehiculos(){
        return vehiculoRepositorio.findAll();
    }

    //Crear vehiculo (Vehiculo nvoVehiculo, string codigoCiudad): crea un vehiculo y lo vincula con la
    //ciudad donde el vehiculo se encuentra, con la entrada proveniente del Frontend.
    public Vehiculo crearVehiculo(Vehiculo nvoVehiculo, int idCiudad) {
    if (ciudadRepositorio.existsById(idCiudad)) {
        Ciudad ciudad = ciudadRepositorio.findById(idCiudad).get();
        nvoVehiculo.setCiudad(ciudad);
        return vehiculoRepositorio.save(nvoVehiculo);
    }
    return null;
}

    //Mantenimiento (int codigoVehiculo, Mantenimiento nvoMantenimiento): se le asocia un
    //registro de mantenimiento si el vehiculo fue devuelto con algún daño que se ocupa reparar.
    public Mantenimiento crearMantenimiento(int vin, Mantenimiento nvoMantenimiento) {
        Vehiculo vehiculo = vehiculoRepositorio.findById(vin).get();

        if (vehiculo != null) {
            nvoMantenimiento.setVehiculo(vehiculo);
            return mantenimientoRepositorio.save(nvoMantenimiento);
        }
        return null;
    }

    //BuscarPorTipo o BuscarPorMarca(String tipo o marca): devuelve los autos que sean de ese mismo tipo.
    public List<Vehiculo> buscarPorMarca(String marca) {
        return vehiculoRepositorio.findByMarca(marca);
    }

    //BuscarPorModelo(String modelo): devuelve una lista de vehículos de la misma marca.
    public List<Vehiculo> buscarPorModelo(String modelo) {
        return vehiculoRepositorio.findByModelo(modelo);
    }

}
