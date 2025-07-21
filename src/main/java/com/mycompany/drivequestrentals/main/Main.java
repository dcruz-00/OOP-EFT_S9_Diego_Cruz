package com.mycompany.drivequestrentals.main;

import com.mycompany.drivequestrentals.model.*;
import com.mycompany.drivequestrentals.servicios.Calculable;
import com.mycompany.drivequestrentals.utils.GestorVehiculos;
import com.mycompany.drivequestrentals.archivos.ArchivoVehiculos;
import java.util.Scanner;

public class Main {
    private static final GestorVehiculos gestor = new GestorVehiculos();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1 -> agregarVehiculo();
                case 2 -> listarVehiculos();
                case 3 -> mostrarBoletas();
                case 4 -> mostrarArriendosLargos();
                case 5 -> ArchivoVehiculos.guardarVehiculos(gestor.getFlota());
                case 6 -> ArchivoVehiculos.cargarVehiculos(gestor.getFlota());
                case 7 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion invalida. Intente nuevamente.");
            }

            System.out.println(); 
        } while (opcion != 7);
    }

    private static void mostrarMenu() {
        System.out.println("=== MENU DE GESTION DE VEHICULOS ===");
        System.out.println("1. Agregar nuevo vehiculo");
        System.out.println("2. Listar vehiculos");
        System.out.println("3. Mostrar boletas");
        System.out.println("4. Mostrar vehiculos con arriendo largo (mayor o igual a 7 dias)");
        System.out.println("5. Guardar vehiculos en archivo");
        System.out.println("6. Cargar vehiculos desde archivo");
        System.out.println("7. Salir");
    }

    private static void agregarVehiculo() {
        System.out.println("--- Agregar Vehiculo ---");
        String tipo = leerTexto("Ingrese tipo (CARGA/PASAJEROS): ").toUpperCase();
        String patente = leerTexto("Patente: ");
        String marca = leerTexto("Marca: ");
        String modelo = leerTexto("Modelo: ");
        int anio = leerEntero("Año: ");
        int dias = leerEntero("Dias de arriendo: ");
        double valor = leerDecimal("Valor diario: ");

        Vehiculo v = null;

        if (tipo.equals("CARGA")) {
            double capacidad = leerDecimal("Capacidad de carga (toneladas): ");
            v = new VehiculoCarga(patente, marca, modelo, anio, dias, valor, capacidad);
        } else if (tipo.equals("PASAJEROS")) {
            int pasajeros = leerEntero("Max. pasajeros: ");
            v = new VehiculoPasajeros(patente, marca, modelo, anio, dias, valor, pasajeros);
        } else {
            System.out.println("Tipo de vehiculo invalido.");
            return;
        }

        if (gestor.agregarVehiculo(v)) {
            System.out.println("Vehiculo agregado correctamente.");
        }
    }

    private static void listarVehiculos() {
        System.out.println("--- Lista de Vehiculos ---");
        gestor.listarVehiculos();
    }

    private static void mostrarBoletas() {
        System.out.println("--- Boletas ---");
        for (Vehiculo v : gestor.getFlota()) {
            if (v instanceof Calculable c) {
                System.out.println("Boleta para vehiculo: " + v.getPatente());
                c.calcularBoleta();
                System.out.println("-----------------------");
            }
        }
    }

    private static void mostrarArriendosLargos() {
        System.out.println("--- Vehiculos con arriendo mayor o igual a 7 dias ---");
        for (Vehiculo v : gestor.obtenerArriendosLargos()) {
            v.mostrarDatos();
            System.out.println("-----------------------");
        }
        System.out.println("Total: " + gestor.contarArriendosLargos());
    }

    // Métodos auxiliares para leer entrada
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    private static double leerDecimal(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero decimal valido.");
            }
        }
    }
}
