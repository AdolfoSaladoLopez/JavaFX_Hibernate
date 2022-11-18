package com.example.javafx;

import models.Pedido;


public class SessionData {

    private static Pedido pedido;

    public static Pedido getPedido() {
        return pedido;
    }

    public static void setPedido(Pedido pedido) {
        SessionData.pedido = pedido;
    }


}