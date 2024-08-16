package hn.unah.proyecto.rentacar.servicios;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.rentacar.modelos.Alquiler;
import hn.unah.proyecto.rentacar.modelos.Cliente;
import hn.unah.proyecto.rentacar.modelos.EEV;
import hn.unah.proyecto.rentacar.modelos.Pago;
import hn.unah.proyecto.rentacar.modelos.Vehiculo;
import hn.unah.proyecto.rentacar.repositorios.ClienteRepositorio;
import hn.unah.proyecto.rentacar.repositorios.VehiculoRepositorio;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;

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
    public Cliente crearAlquiler(int idCliente, long vin, Alquiler nvoAlquiler){
        Vehiculo vehiculo = this.vehiculoRepositorio.findById(vin).get();
        vehiculo.setDisponibilidad(false);
        if (comprobarExistencia(idCliente)){
            Cliente consultarCliente = this.clienteRepositorio.findById(idCliente).get();
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

    //Falta calcular el pago final si el auto fue devuelto en otra ciudad
    public Cliente finAlquiler(int idCliente, EEV nvoEev){
        Cliente consultarCliente = this.clienteRepositorio.findById(idCliente).get();
        List<Alquiler> clienteAlquilers = consultarCliente.getAlquiler();
        Alquiler alquiler = clienteAlquilers.get(clienteAlquilers.size()-1);
        Pago estadoPago = alquiler.getPago();
        estadoPago.setEstadoPago(true);
        alquiler.setPago(estadoPago);
        Vehiculo disponibilidadVehiculo = alquiler.getVehiculo();
        List<EEV> eev = disponibilidadVehiculo.getEevs();
        nvoEev.setVehiculoEev(disponibilidadVehiculo);
        eev.add(nvoEev);
        disponibilidadVehiculo.setEevs(eev);
        if(nvoEev.getCostoEstimado()==0){
            disponibilidadVehiculo.setDisponibilidad(true);
            alquiler.setVehiculo(disponibilidadVehiculo);
        }
        clienteAlquilers.set(clienteAlquilers.size()-1, alquiler);
        return this.clienteRepositorio.save(consultarCliente);
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

    public boolean comprobarExistencia(int idCliente){
        return this.clienteRepositorio.existsById(idCliente);
    }    
}
