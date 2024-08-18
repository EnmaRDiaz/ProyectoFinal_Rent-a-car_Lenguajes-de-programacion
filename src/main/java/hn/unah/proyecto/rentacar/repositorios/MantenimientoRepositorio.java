package hn.unah.proyecto.rentacar.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.proyecto.rentacar.modelos.Mantenimiento;

@Repository
public interface MantenimientoRepositorio extends JpaRepository<Mantenimiento, Integer> {

}
