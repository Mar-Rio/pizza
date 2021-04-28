/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cosca
 */
public class RegistroJornadaLaboral {

    private Map<Pedidos, Path> pedidosHoy;

    public RegistroJornadaLaboral() {
        this.pedidosHoy = new HashMap<>();
    }

    public Map<Pedidos, Path> getPedidosHoy() {
        return pedidosHoy;
    }

    public void setPedidosHoy(Pedidos pedido, Path ruta) {
        this.pedidosHoy.put(pedido, ruta);
    }

}
