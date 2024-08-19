package hn.unah.proyecto.rentacar.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.proyecto.rentacar.modelos.Ciudad;


@Repository
public interface ciudadRepositorio extends JpaRepository<Ciudad, Integer> {

    Ciudad findByNombre(String nombre);

    boolean existsByNombre(String nombre);

}
