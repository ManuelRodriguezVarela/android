package net.ies.san.clemente.tarea_pmdm02.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoPrimeNumber {
    public PrimeNumber getPrimeNumberFromFile(File file) {
        PrimeNumber entidad = null;
        BufferedReader buferEntrada;

        try {
            //Creamos un flujo de entrada para el archivo
            FileReader fluxoDatos = new FileReader(file);
            //Creamos el bufer de entrada
            buferEntrada = new BufferedReader(fluxoDatos);
            //leemos linea a linea
            StringBuilder jsonBuilder = new StringBuilder();
            String linea;
            while ((linea=buferEntrada.readLine()) != null) {
                jsonBuilder.append(linea).append("\n");
            }
            //Construimos el String con todas las lineas leidas
            String json = jsonBuilder.toString();
            //Pasamos el json la clase con la que se corresponde
            Gson gson = new Gson();
            entidad = gson.fromJson(json, PrimeNumber.class);
            //Tenemos que cerrar siempre el fichero
            buferEntrada.close();

        } catch (IOException ex) {
            Logger.getLogger(DaoPrimeNumber.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return entidad;
    }

    public void writeJsonInFile(PrimeNumber entidad, File file) {
        //Pasamos la clase a JSON utilizando a libreria GSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(entidad);
        try {
            //Creamos flujo de salida
            FileWriter fluxoDatos;
            fluxoDatos = new FileWriter(file);
            BufferedWriter buferSaida = new BufferedWriter(fluxoDatos);
            buferSaida.write(json);
            //Cerramos el archivo
            buferSaida.close();
        } catch (IOException ex) {
            Logger.getLogger(DaoPrimeNumber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
