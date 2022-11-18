package com.example.javafx;

import models.Pedido;

import java.util.List;

public interface PedidoDAO {
    public Boolean crearPedido(Pedido pedido);

    public Boolean actualizarPedido(Pedido pedido);

    public Pedido obtenerPedido(Integer id);

    public Boolean eliminarPedido(Pedido pedido);

    public List<Pedido> obtenerListadoPedidos();

    public List<Pedido> obtenerPedidosPendientesHoy();
}
