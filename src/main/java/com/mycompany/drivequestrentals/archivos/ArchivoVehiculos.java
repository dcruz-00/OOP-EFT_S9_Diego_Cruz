package com.mycompany.drivequestrentals.archivos;

import com.mycompany.drivequestrentals.model.*;
import java.io.*;
import java.util.List;

public class ArchivoVehiculos {
    
    private static final String NOMBRE_ARCHIVO_RECURSOS = "vehiculos.txt";
    
    private static final String RUTA_ARCHIVO_ESCRITURA = "vehiculos_guardados.txt";
    
    public static void guardarVehiculos(List<Vehiculo> lista) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO_ESCRITURA))) {
        for (Vehiculo v : lista) {
            String linea = "";

            if (v instanceof VehiculoCarga) {
                VehiculoCarga c = (VehiculoCarga) v;   
                linea = String.format("CARGA,%s,%s,%s,%d,%d,%.2f,%.2f",
                        c.getPatente(), c.getMarca(), c.getModelo(), c.getAnio(),
                        c.getDiasArriendo(), c.getValorDiario(), c.getCapacidadCarga());
            } else if (v instanceof VehiculoPasajeros) {
                VehiculoPasajeros p = (VehiculoPasajeros) v;
                linea = String.format("PASAJEROS,%s,%s,%s,%d,%d,%.2f,%d",
                        p.getPatente(), p.getMarca(), p.getModelo(), p.getAnio(),
                        p.getDiasArriendo(), p.getValorDiario(), p.getMaxPasajeros());
            }

            bw.write(linea);
            bw.newLine();
        }
        System.out.println("Vehiculos guardados correctamente en el archivo.");
    } catch (IOException e) {
        System.out.println("Error al guardar vehiculos: " + e.getMessage());
    }
}

    // Carga vehículos desde resources/vehiculos.txt
    public static void cargarVehiculos(List<Vehiculo> lista) {
        lista.clear();

        try (InputStream is = ArchivoVehiculos.class.getClassLoader().getResourceAsStream(NOMBRE_ARCHIVO_RECURSOS)) {
            if (is == null) {
                System.out.println("Archivo " + NOMBRE_ARCHIVO_RECURSOS + " no encontrado en /resources");
                return;
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    try {
                        String[] datos = linea.split(";");
                        if (datos.length < 8) {
                            System.out.println("Formato invalido en linea: " + linea);
                            continue;
                        }
                        String tipo = datos[0];
                        String patente = datos[1];
                        String marca = datos[2];
                        String modelo = datos[3];
                        int anio = Integer.parseInt(datos[4]);
                        int valorDiario = Integer.parseInt(datos[5]);
                        int diasArriendo = Integer.parseInt(datos[6]);

                        if (tipo.equalsIgnoreCase("carga")) {
                            double capacidad = Double.parseDouble(datos[7]);
                            VehiculoCarga v = new VehiculoCarga(patente, marca, modelo, anio, diasArriendo, valorDiario, capacidad);
                            lista.add(v);
                        } else if (tipo.equalsIgnoreCase("pasajero")) {
                            int maxPasajeros = Integer.parseInt(datos[7]);
                            VehiculoPasajeros v = new VehiculoPasajeros(patente, marca, modelo, anio, diasArriendo, valorDiario, maxPasajeros);
                            lista.add(v);
                        } else {
                            System.out.println("Tipo desconocido en linea: " + linea);
                        }
                    } catch (Exception ex) {
                        System.out.println("Error al procesar linea: " + linea);
                        ex.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
    }
}