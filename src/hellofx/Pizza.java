/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hellofx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cosca
 */
public class Pizza {

    private String masa = "",
            tipo = "",
            tamanyo = "";
    private List<String> ingredientesExtra;

    public void setMasa(String masa) {
        this.masa = masa;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTamanyo(String tamanyo) {
        this.tamanyo = tamanyo;
    }

    public void setIngredientesExtra(String ingrediente) {
        this.ingredientesExtra.add(ingrediente);
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
        Map<String, Double> precios = new HashMap<>();
        precios.put("normal", 3.0);
        precios.put("integral", 3.5);
        precios.put("carbonara", 3.0);
        precios.put("cuatroQuesos", 5.0);
        precios.put("barbacoa", 7.0);
        precios.put("romana", 3.5);
        precios.put("jamon", 0.5);
        precios.put("queso", 0.75);
        precios.put("tomate", 1.5);
        precios.put("cebolla", 0.75);
        precios.put("olivas", 1.0);
        precios.put("champiñones", 1.25);
return 0.0;
    }
}
