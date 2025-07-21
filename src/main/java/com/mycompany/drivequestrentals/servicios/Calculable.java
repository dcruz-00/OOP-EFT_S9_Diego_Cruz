package com.mycompany.drivequestrentals.servicios;

public interface Calculable {
    double IVA = 0.19;
    double DESCUENTO_CARGA = 0.07;
    double DESCUENTO_PASAJEROS = 0.12;

    void calcularBoleta();
}
