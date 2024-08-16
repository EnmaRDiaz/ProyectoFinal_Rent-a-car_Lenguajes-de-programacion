package hn.unah.proyecto.rentacar.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.proyecto.rentacar.modelos.Alquiler;

@Repository
public interface  AlquilerRepositorio extends JpaRepository<Alquiler, Integer> {

    List<Alquiler> findByFechaFinBetween(LocalDate fechaInicio, LocalDate fechaFin);

}
