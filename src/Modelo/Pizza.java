/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javafx.collections.ObservableList;

/**
 *
 * @author cosca
 */
public class Pizza {

    private String masa,
            tipo,
            tamanyo;
    private ArrayList<String> ingredientesExtra;
  
    private final Double incrementoMediana = 1.15,
            incrementoFamiliar = 1.30;
    private Precio precios;

    public Pizza() {
        precios = new Precio(1.15,1.3);
       
    }

    public void setMasa(String masa) {
        this.masa = masa;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTamanyo(String tamanyo) {
        this.tamanyo = tamanyo;
    }

    public void setIngredientesExtra(ObservableList<String> ingredientes) {
        this.ingredientesExtra = new ArrayList<>(ingredientes);
    }

    public String getMasa() {
        return masa;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTamanyo() {
        return tamanyo;
    }

    public List<String> getIngredientesExtra() {
        return ingredientesExtra;
    }

    public Double calcularPrecio() {
        Double precioFinal = 0.0;
        precioFinal += precios.getPrecios().get(masa);
        precioFinal += precios.getPrecios().get(tipo);
        if (!ingredientesExtra.isEmpty()) {
            for (String ingrediente : ingredientesExtra) {
                if (precios.getPrecios().containsKey(ingrediente)) {
                    precioFinal += precios.getPrecios().get(ingrediente);
                }
            }
        }
        if (tamanyo.equalsIgnoreCase("familiar")) {
            precioFinal = precioFinal * incrementoFamiliar;
        }
        if (tamanyo.equalsIgnoreCase("mediana")) {
            precioFinal = precioFinal * incrementoMediana;
        }
        return precioFinal;
    }

    public String composicion() {
        String texto = "MASA: " + masa + " + " + precios.getPrecios().get(masa) + "$\n";
        texto += "TIPO: " + tipo + " + " + precios.getPrecios().get(tipo) + "$\n";
       
        if (tamanyo.equalsIgnoreCase("mediana")) {
            texto += "TAMAÑO: " + tamanyo + " + " + String.format("%.2f", (incrementoMediana - 1) * 100) + "%\n";
        } else if (tamanyo.equalsIgnoreCase("familiar")) {
            texto += "TAMAÑO: " + tamanyo + " + " + String.format("%.2f", (incrementoFamiliar - 1) * 100) + "%\n";
        } else {
            texto += "TAMAÑO: Pequeña + 0.0%\n";
        }
         if (!ingredientesExtra.isEmpty()) {
            texto += "INGREDIENTES EXTRA: ";
            for (int i = 0; i < ingredientesExtra.size(); i++) {
                texto += ingredientesExtra.get(i) + " +" + precios.getPrecios().get(ingredientesExtra.get(i)) + "  ";
            }
        }
        return texto;

    }
}
