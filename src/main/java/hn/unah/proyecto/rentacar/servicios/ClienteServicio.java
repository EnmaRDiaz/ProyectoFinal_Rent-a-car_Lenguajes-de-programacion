package hn.unah.proyecto.rentacar.servicios;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.rentacar.dtos.registroVentas;
import hn.unah.proyecto.rentacar.modelos.Alquiler;
import hn.unah.proyecto.rentacar.modelos.Cliente;
import hn.unah.proyecto.rentacar.modelos.EEV;
import hn.unah.proyecto.rentacar.modelos.Pago;
import hn.unah.proyecto.rentacar.modelos.Vehiculo;
import hn.unah.proyecto.rentacar.repositorios.AlquilerRepositorio;
import hn.unah.proyecto.rentacar.repositorios.ciudadRepositorio;
import hn.unah.proyecto.rentacar.repositorios.ClienteRepositorio;
import hn.unah.proyecto.rentacar.repositorios.VehiculoRepositorio;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;

    @Autowired
    private ciudadRepositorio ciudadRepositorio;

    @Autowired
    private AlquilerRepositorio alquilerRepositorio;

    private final int DIFERENCIA_ENTREGA = 1000;

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

    //Mejorar Codigo
    public Cliente crearAlquiler(int idCliente, int vin, Alquiler nvoAlquiler) {
        Vehiculo vehiculo = this.vehiculoRepositorio.findById(vin).orElse(null);
    
        if (vehiculo == null) {
            return null;
        }
    
        if (comprobarExistencia(idCliente) 
            && Boolean.TRUE.equals(vehiculo.getDisponibilidad()) // Maneja el caso donde disponibilidad puede ser null
            && comprobarUltimoAlquiler(idCliente)) {
    
            vehiculo.setDisponibilidad(false);
            Cliente consultarCliente = this.clienteRepositorio.findById(idCliente).orElse(null);
    
            if (consultarCliente == null) {
                return null;
            }
    
            Pago pago = crearPago(nvoAlquiler, vehiculo);
            nvoAlquiler.setCostoTotal(pago.getMonto());
            nvoAlquiler.setPago(pago);
            nvoAlquiler.setVehiculo(vehiculo);
    
            List<Alquiler> alquilerCliente = consultarCliente.getAlquiler();
            nvoAlquiler.setCliente(consultarCliente);
    
            List<Alquiler> alquilerVehiculo = vehiculo.getAlquilers();
            alquilerVehiculo.add(nvoAlquiler);
            vehiculo.setAlquilers(alquilerVehiculo);
    
            alquilerCliente.add(nvoAlquiler);
            consultarCliente.setAlquiler(alquilerCliente);
    
            return this.clienteRepositorio.save(consultarCliente);
        }
    
        return null;
    }    

    public Cliente finAlquiler(int idCliente, String ciudadEntrega, EEV nvoEev){
        //Consultar al cliente y conseguir su alquiler mas reciente.
        
        Cliente consultarCliente = this.clienteRepositorio.findById(idCliente).get();
        List<Alquiler> clienteAlquilers = consultarCliente.getAlquiler();
        if(!clienteAlquilers.isEmpty()){
            Alquiler alquiler = clienteAlquilers.get(clienteAlquilers.size()-1);
            //Finalizar el pago
            Pago estadoPago = alquiler.getPago();
            Vehiculo disponibilidadVehiculo = alquiler.getVehiculo();
            //añade al precio si el auto fue entregado en otra ciudad y le cambia la ciudad al auto
            if(!alquiler.getCiudadOrigen().equals(ciudadEntrega)){
                alquiler.setCiudadEntrega(ciudadEntrega);
                estadoPago = recalculoDePago(estadoPago);
                disponibilidadVehiculo.setCiudad(this.ciudadRepositorio.findByNombre(ciudadEntrega));
            }
            estadoPago.setEstadoPago(true);
            estadoPago.setMonto(estadoPago.getMonto()+nvoEev.getCostoEstimado());
            alquiler.setCostoTotal(estadoPago.getMonto());
            alquiler.setPago(estadoPago);
            //Sacar el vehiculo del alquiler, y añadir el EEV
            List<EEV> eev = disponibilidadVehiculo.getEevs();
            nvoEev.setVehiculo(disponibilidadVehiculo);             // Cambiado de nvoEev.setVehiculoEev(disponibilidadVehiculo) a nvoEev.setVehiculo(disponibilidadVehiculo);
            eev.add(nvoEev);
            disponibilidadVehiculo.setEevs(eev);
            //Determinar si el auto tiene daños y si no los tiene poner el auto como disponible
            if(nvoEev.getCostoEstimado()==0){
                disponibilidadVehiculo.setDisponibilidad(true);
                alquiler.setVehiculo(disponibilidadVehiculo);
            }
            //Actualizar el alquiler del registro de alquileres
            clienteAlquilers.set(clienteAlquilers.size()-1, alquiler);
            return this.clienteRepositorio.save(consultarCliente);
        }
        return null;
    }

    public registroVentas registroEntreFechas(LocalDate fechaInicio, LocalDate fechaFin){
        registroVentas informe = new registroVentas();
        List<Alquiler> alquilerEnMedio = this.alquilerRepositorio.findByFechaFinBetween(fechaInicio, fechaFin);
        List<Cliente> clientesEnMedio = this.clienteRepositorio.findByFechaRegistroBetween(fechaInicio, fechaFin);
        double gananciaNeta = 0.0;
        for (Alquiler calculoAlquiler: alquilerEnMedio) {
            gananciaNeta += calculoAlquiler.getCostoTotal(); 
        }
        informe.setClientesNuevos(clientesEnMedio.size());
        informe.setGananciaNeta(gananciaNeta);
        informe.setNumeroAlquiler(alquilerEnMedio.size());

        return informe;
    } 

    public Pago crearPago(Alquiler alquiler, Vehiculo vehiculo){
        int diasEntre = (int) ChronoUnit.DAYS.between(alquiler.getFechaInicio(), alquiler.getFechaFin());
        Pago nvoPago = new Pago();
        nvoPago.setFechaPago(alquiler.getFechaFin());
        nvoPago.setAlquiler(alquiler);
        nvoPago.setEstadoPago(false);
        double monto = vehiculo.getPrecioDiario()*diasEntre;
        nvoPago.setMonto(monto);
        return nvoPago;
    }

    public Pago recalculoDePago(Pago pago){
        double monto = pago.getMonto()+DIFERENCIA_ENTREGA;
        pago.setMonto(monto);
        return pago;
    }

    //Comprueba que el ultimo alquiler sea pagado
    public boolean comprobarUltimoAlquiler(int idCliente){
        Cliente cliente = this.clienteRepositorio.findById(idCliente).get();
        if(cliente.getAlquiler().isEmpty()){
            return true;
        }
        List<Alquiler> Alquilers = cliente.getAlquiler();
        Alquiler ultimoAlquiler = Alquilers.get(Alquilers.size()-1);
        Pago pagoAlquiler = ultimoAlquiler.getPago();
        return pagoAlquiler.isEstadoPago();
    }

    public boolean comprobarExistencia(int idCliente){
        return this.clienteRepositorio.existsById(idCliente);
    }    
}
