/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cosca
 */
public class Precio {
private final Map<String, Double> precios = new HashMap<>();
private Double tamanyoMediana,
        tamanyoFamiliar;

    public Precio(Double tamanyoMediana, Double tamanyoFamiliar) {
        this.tamanyoMediana = tamanyoMediana;
        this.tamanyoFamiliar = tamanyoFamiliar;
        precios.put("Normal", 3.0);
        precios.put("Integral", 3.5);
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

    public Map<String, Double> getPrecios() {
        return precios;
    }

    public Double getTamanyoMediana() {
        return tamanyoMediana;
    }

    public Double getTamanyoFamiliar() {
        return tamanyoFamiliar;
    }

    public void setTamanyoMediana(Double tamanyoMediana) {
        this.tamanyoMediana = tamanyoMediana;
    }

    public void setTamanyoFamiliar(Double tamanyoFamiliar) {
        this.tamanyoFamiliar = tamanyoFamiliar;
    }

public Boolean cambiarPrecios(String palabraClave, Double precioNuevo) {
    //devuelve false si el string no existe en Map.
    Boolean cambiado = false;
    if(precios.containsKey(palabraClave)) {
         cambiado = precios.replace(palabraClave, precios.get(palabraClave), precioNuevo);
    }
    return cambiado;
}
public Boolean anyadirNuevaOpcion(String palabraClave, Double precio) {
      //devuelve false si ya existe.
      Boolean existePreviamente = false;
      if(!precios.containsKey(palabraClave)) {
          precios.put(palabraClave, precio);
          existePreviamente = true;
  }
      return existePreviamente;
}
}
