package hn.unah.proyecto.rentacar.servicios;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.rentacar.modelos.Alquiler;
import hn.unah.proyecto.rentacar.modelos.Cliente;
import hn.unah.proyecto.rentacar.modelos.Pago;
import hn.unah.proyecto.rentacar.repositorios.AlquilerRepositorio;
import hn.unah.proyecto.rentacar.repositorios.ClienteRepositorio;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private AlquilerRepositorio alquilerRepositorio;

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

    //Falta Implementar vehiculo, calculo del pago, asociacion del pago
    public Cliente crearAlquiler(int idCliente, Alquiler nvoAlquiler){
        if (comprobarExistencia(idCliente)){
            Cliente consultarCliente = this.clienteRepositorio.findById(idCliente).get();
            Pago pago = crearPago(nvoAlquiler);
            nvoAlquiler.setCostoTotal(pago.getMonto());
            nvoAlquiler.setPago(pago);
            List<Alquiler> alquiler = consultarCliente.getAlquiler();
            nvoAlquiler.setCliente(consultarCliente);
            alquiler.add(nvoAlquiler);
            consultarCliente.setAlquiler(alquiler);
            return this.clienteRepositorio.save(consultarCliente);
        }
        return null;
    }

    //Falta el vehiculo que dice cuanto vale el carro por dia
    public Pago crearPago(Alquiler alquiler){
        int diasEntre = (int) ChronoUnit.DAYS.between(alquiler.getFechaInicio(), alquiler.getFechaFin());
        Pago nvoPago = new Pago();
        nvoPago.setFechaPago(alquiler.getFechaFin());
        nvoPago.setAlquiler(alquiler);
        nvoPago.setEstadoPago(false);
        //faltaVehiculo
        double monto = 100.10*diasEntre;
        nvoPago.setMonto(monto);
        return nvoPago;
    }

    public boolean comprobarExistencia(int idCliente){
        return this.clienteRepositorio.existsById(idCliente);
    }    
}
