package com.mycompany.drivequestrentals.model;

import com.mycompany.drivequestrentals.servicios.Calculable;

public class VehiculoCarga extends Vehiculo implements Calculable {
    private double capacidadCarga;

    public VehiculoCarga() {
        super();
    }

    public VehiculoCarga(String patente, String marca, String modelo, int anio, int diasArriendo, double valorDiario, double capacidadCarga) {
        super(patente, marca, modelo, anio, diasArriendo, valorDiario);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("=== Vehiculo de Carga ===");
        System.out.println("Patente: " + patente);
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Año: " + anio);
        System.out.println("Dias de arriendo: " + diasArriendo);
        System.out.println("Valor diario: $" + valorDiario);
        System.out.println("Capacidad de carga: " + capacidadCarga + " toneladas");
    }

    @Override
    public void calcularBoleta() {
        double bruto = valorDiario * diasArriendo;
        double iva = bruto * IVA;
        double descuento = bruto * DESCUENTO_CARGA;
        double total = bruto + iva - descuento;

        System.out.println("--- Boleta Vehiculo Carga ---");
        System.out.printf("Subtotal: $%.2f\n", bruto);
        System.out.printf("IVA (19%%): $%.2f\n", iva);
        System.out.printf("Descuento (7%%): $%.2f\n", descuento);
        System.out.printf("Total a pagar: $%.2f\n", total);
    }
}
