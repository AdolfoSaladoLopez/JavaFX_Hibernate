package com.example.javafx;

import models.Pedido;
import models.Producto;

public class SessionData {

    private static Pedido pedido;
    private static Producto producto;

    public static Pedido getPedido() {
        return pedido;
    }

    public static void setPedido(Pedido pedido) {
        SessionData.pedido = pedido;
    }

    public static Producto getProducto() {
        return producto;
    }

    public static void setProducto(Producto producto) {
        SessionData.producto = producto;
    }
}