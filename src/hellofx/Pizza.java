/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hellofx;

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
    private final Map<String, Double> precios = new HashMap<>();
    private final Double incrementoMediana = 1.15,
            incrementoFamiliar = 1.30;

    public Pizza() {
        precios.put("normal", 3.0);
        precios.put("integral", 3.5);
        precios.put("Carbonara", 3.0);
        precios.put("Cuatro Quesos", 5.0);
        precios.put("Barbacoa", 7.0);
        precios.put("Romana", 3.5);
        precios.put("Jamón", 0.5);
        precios.put("Queso", 0.75);
        precios.put("Tomate", 1.5);
        precios.put("Cebolla", 0.75);
        precios.put("Olivas", 1.0);
        precios.put("Champiñones", 1.25);
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
        precioFinal += precios.get(masa);
        precioFinal += precios.get(tipo);
        if (!ingredientesExtra.isEmpty()) {
            for (String ingrediente : ingredientesExtra) {
                if (precios.containsKey(ingrediente)) {
                    precioFinal += precios.get(ingrediente);
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
        String texto = "MASA: " + masa + " + " + precios.get(masa) + "$\n";
        texto += "TIPO: " + tipo + " + " + precios.get(tipo) + "$\n";
        if (!ingredientesExtra.isEmpty()) {
            texto += "INGREDIENTES EXTRA: ";
            for (int i = 0; i < ingredientesExtra.size(); i++) {
                texto += ingredientesExtra.get(i) + " + " + precios.get(ingredientesExtra.get(i)) + "   ";
            }
        }
        if (tamanyo.equalsIgnoreCase("mediana")) {
            texto += "TAMAÑO: " + tamanyo + " + " + String.format("%.2f", (incrementoMediana - 1) * 100) + "%";
        } else if (tamanyo.equalsIgnoreCase("familiar")) {
            texto += "TAMAÑO: " + tamanyo + " + " + String.format("%.2f", (incrementoFamiliar - 1) * 100) + "%";
        } else {
            texto += "TAMAÑO: Pequeña + 0.0%";
        }
        return texto;

    }
}
