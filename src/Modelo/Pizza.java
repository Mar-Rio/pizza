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
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author cosca
 */
public class Pizza {

    private String masa,
            tipo,
            tamanyo,
            bebida,
            gratinado,
            descripcionPizza;
    public static int contador = 1;
    private int numeroPizza;
    private ArrayList<String> ingredientesExtra;
    private Precio precios;

    public Pizza() {
        numeroPizza = contador++;
        precios = new Precio();

    }

    public void setNumeroPizza(int numeroPizza) {
        this.numeroPizza = numeroPizza;
    }

    public String getDescripcionPizza() {
        return descripcionPizza;
    }

    public void setDescripcionPizza(String descripcionPizza) {
        this.descripcionPizza = descripcionPizza;
    }

    public int getNumeroPizza() {
        return numeroPizza;
    }

    public String getGratinado() {
        return gratinado;
    }

    public void setGratinado(String gratinado) {
        this.gratinado = gratinado;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
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
            precioFinal *= precios.getTamanyoFamiliar();
        } else if (tamanyo.equalsIgnoreCase("mediana")) {
            precioFinal *= precios.getTamanyoMediana();
        }
        if (gratinado.equalsIgnoreCase("gratinada")) {
            precioFinal *= precios.getPizzaGratinada();
        }
        if (bebida.equalsIgnoreCase("incluir bebida")) {
            precioFinal += precios.getPrecios().get(bebida);
        }

        return precioFinal;
    }

    public String composicion() {

        String texto = "MASA: " + masa + " + " + precios.getPrecios().get(masa) + "$\n";
        texto += "TIPO: " + tipo + " + " + precios.getPrecios().get(tipo) + "$\n";
        if (tamanyo.equalsIgnoreCase("mediana")) {
            texto += "TAMAÑO: " + tamanyo + " + " + String.format("%.0f", (precios.getTamanyoMediana() - 1) * 100) + "%\n";
        } else if (tamanyo.equalsIgnoreCase("familiar")) {
            texto += "TAMAÑO: " + tamanyo + " + " + String.format("%.0f", (precios.getTamanyoFamiliar() - 1) * 100) + "%\n";
        } else {
            texto += "TAMAÑO: Pequeña + 0.0%\n";
        }
        if (!ingredientesExtra.isEmpty()) {
            texto += "INGREDIENTES EXTRA: \n";
            for (int i = 0; i < ingredientesExtra.size(); i++) {
                texto += ingredientesExtra.get(i) + " +" + precios.getPrecios().get(ingredientesExtra.get(i)) + "$\n";
            }
        }
        if (gratinado.equalsIgnoreCase("gratinada")) {
            texto += gratinado.toUpperCase() + " + " + String.format("%.0f", (precios.getPizzaGratinada() - 1) * 100) + "%\n";
        }
        if (bebida.equalsIgnoreCase("incluir bebida")) {
            texto += bebida.toUpperCase() + "  +" + precios.getPrecios().get(bebida) + "$";
        }
        return texto;

    }
}
