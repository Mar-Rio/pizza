/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import hellofx.Controller;

/**
 *
 * @author cosca
 */
public class Pedidos {

    private int numeroPedido;
    private Map<Pizza, Double> pizzasPedido = new LinkedHashMap<>();
    private static int contador = 1;
    private double precioPedido = 0;

    public Pedidos() {
        numeroPedido = contador++;
    }

    public double getPrecioPedido() {
        Double precioTotal = 0.0;
        for (Entry<Pizza, Double> pizza : pizzasPedido.entrySet()) {
            precioTotal += pizza.getValue();
        }
        precioPedido = precioTotal;
        return precioTotal;
    }

    public void setPrecioPedido(double precioPedido) {
        this.precioPedido = precioPedido;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Map<Pizza, Double> getPizzasPedido() {
        return pizzasPedido;
    }

    public void eliminarPizzasPedido() {
        pizzasPedido.clear();
        Pizza.contador = 1;
        contador -= 1;
    }

    public void añadirPizzaPedido(Pizza pizza, Double precio) {
        pizzasPedido.put(pizza, precio);
        getPrecioPedido();
    }

    public void eliminarPizza(int pizza) {
        List keys = new ArrayList(pizzasPedido.keySet());
        Pizza pizzaEliminar = (Pizza) keys.get(pizza);
        pizzasPedido.remove(pizzaEliminar);
    }
  }
