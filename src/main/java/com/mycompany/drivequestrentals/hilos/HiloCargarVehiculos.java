package com.mycompany.drivequestrentals.hilos;

import com.mycompany.drivequestrentals.archivos.ArchivoVehiculos;
import com.mycompany.drivequestrentals.model.Vehiculo;

import java.util.List;

public class HiloCargarVehiculos implements Runnable {

    private final List<Vehiculo> listaVehiculos;

    public HiloCargarVehiculos(List<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    @Override
    public void run() {
        synchronized (listaVehiculos) {
            System.out.println("[HiloCarga] Iniciando carga de vehiculos...");
            ArchivoVehiculos.cargarVehiculos(listaVehiculos);
            System.out.println("[HiloCarga] Carga completada.");
        }
    }
}
