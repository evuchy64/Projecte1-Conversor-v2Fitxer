package com.example.projecte1_conversor_v2fitxer.models;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Monedas {
    private String name;
    private float number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }
    public List<Monedas> readJsonStream(InputStream in) throws IOException {
        // Nueva instancia JsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            // Leer Array
            return leerArrayMonedas(reader);
        } finally {
            reader.close();
        }

    }
    public List leerArrayMonedas(JsonReader reader) throws IOException {
        // Lista temporal
        ArrayList monedas = new ArrayList();

        reader.beginArray();
        while (reader.hasNext()) {
            // Leer objeto
            monedas.add(leerAnimal(reader));
        }
        reader.endArray();
        return monedas;
    }
    public Monedas leerAnimal(JsonReader reader) throws IOException {
        String nameM = null;
        String numberM = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "name":
                    nameM = reader.nextString();
                    break;
                case "number":
                     numberM = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Monedas();
    }
}
