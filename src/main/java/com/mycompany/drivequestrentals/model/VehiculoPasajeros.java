package com.mycompany.drivequestrentals.model;

import com.mycompany.drivequestrentals.servicios.Calculable;

public class VehiculoPasajeros extends Vehiculo implements Calculable {
    private int maxPasajeros;

    public VehiculoPasajeros() {
        super();
    }

    public VehiculoPasajeros(String patente, String marca, String modelo, int anio, int diasArriendo, double valorDiario, int maxPasajeros) {
        super(patente, marca, modelo, anio, diasArriendo, valorDiario);
        this.maxPasajeros = maxPasajeros;
    }

    public int getMaxPasajeros() {
        return maxPasajeros;
    }

    public void setMaxPasajeros(int maxPasajeros) {
        this.maxPasajeros = maxPasajeros;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("=== Vehiculo de Pasajeros ===");
        System.out.println("Patente: " + patente);
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Año: " + anio);
        System.out.println("Dias de arriendo: " + diasArriendo);
        System.out.println("Valor diario: $" + valorDiario);
        System.out.println("Max. Pasajeros: " + maxPasajeros);
    }

    @Override
    public void calcularBoleta() {
        double bruto = valorDiario * diasArriendo;
        double iva = bruto * IVA;
        double descuento = bruto * DESCUENTO_PASAJEROS;
        double total = bruto + iva - descuento;

        System.out.println("--- Boleta Vehiculo Pasajeros ---");
        System.out.printf("Subtotal: $%.2f\n", bruto);
        System.out.printf("IVA (19%%): $%.2f\n", iva);
        System.out.printf("Descuento (12%%): $%.2f\n", descuento);
        System.out.printf("Total a pagar: $%.2f\n", total);
    }
}
