package com.mycompany.drivequestrentals.utils;

import com.mycompany.drivequestrentals.model.Vehiculo;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class GestorVehiculos {
    private List<Vehiculo> flota;

    public GestorVehiculos() {
        this.flota = Collections.synchronizedList(new ArrayList<>());
    }
    
    // Verifica si existe una patente en la flota
    private boolean existePatente(String patente) {
        for (Vehiculo v : flota) {
            if (v.getPatente().equalsIgnoreCase(patente)) {
                return true;
            }
        }
        return false;
    }

    // Agrega un vehículo, verificando que la patente sea única
    public boolean agregarVehiculo(Vehiculo vehiculo) {
        if (existePatente(vehiculo.getPatente())) {
            System.out.println("Error: ya existe un vehiculo con la patente " + vehiculo.getPatente());
            return false;
        }
        flota.add(vehiculo);
        System.out.println("Vehiculo agregado correctamente: " + vehiculo.getPatente());
        return true;
    }

    // Lista todos los vehículos
    public void listarVehiculos() {
        if (flota.isEmpty()) {
            System.out.println("No hay vehiculos registrados.");
            return;
        }

        for (Vehiculo v : flota) {
            v.mostrarDatos();
            System.out.println("-------------------------------");
        }
    }

    public List<Vehiculo> obtenerArriendosLargos() {
        List<Vehiculo> resultado = new ArrayList<>();

        for (Vehiculo v : flota) {
            if (v.getDiasArriendo() >= 7) {
                resultado.add(v);
            }
        }

        return resultado;
    }

    public int contarArriendosLargos() {
        return obtenerArriendosLargos().size();
    }

     public List<Vehiculo> getFlota() {
        return flota;
    }
}
