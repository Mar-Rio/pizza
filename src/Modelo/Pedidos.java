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
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

/**
 *
 * @author cosca
 */
public class Pedidos {

    private int numeroPedido;
    private Map<Pizza, Double> pizzasPedido = new LinkedHashMap<>();
    public static int contador = 1;
    private double precioPedido = 0;
    private LocalDateTime horaPedido;
    private String persona;
    private Path ruta;

    public Pedidos() {
        numeroPedido = contador++;
    }

    public Path getRuta() {
        return ruta;
    }

    public void setRuta(Path ruta) {
        this.ruta = ruta;
    }

    public void setHoraPedido(LocalDateTime horaPedido) {
        this.horaPedido = horaPedido;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public LocalDateTime getHoraPedido() {
        return horaPedido;
    }

    public String getPersona() {
        return persona;
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
