package hn.unah.proyecto.rentacar.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.proyecto.rentacar.modelos.Vehiculo;

@Repository
public interface VehiculoRepositorio extends JpaRepository<Vehiculo, Integer> {
    List<Vehiculo> findByModelo(String modelo);
    List<Vehiculo> findByMarca(String marca);
    List<Vehiculo> findByDisponibilidad(Boolean disponibilidad);
}
