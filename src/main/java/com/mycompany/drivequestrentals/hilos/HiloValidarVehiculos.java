package com.mycompany.drivequestrentals.hilos;

import com.mycompany.drivequestrentals.model.Vehiculo;

import java.util.List;

public class HiloValidarVehiculos implements Runnable {

    private final List<Vehiculo> listaVehiculos;

    public HiloValidarVehiculos(List<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    @Override
    public void run() {
        synchronized (listaVehiculos) {
            System.out.println("[HiloValidacion] Validando vehiculos con arriendo >= 7 dias...");
            for (Vehiculo v : listaVehiculos) {
                if (v.getDiasArriendo() >= 7) {
                    System.out.println("[VALIDO] " + v.getPatente() + " (" + v.getDiasArriendo() + " dias)");
                }
            }
            System.out.println("[HiloValidacion] Validacion completada.");
        }
    }
}
