package com.example.javafx;

import java.util.List;

import models.Pedido;
import org.hibernate.Transaction;


public class PedidoDAOHib implements PedidoDAO {

    @Override
    public Boolean crearPedido(Pedido pedido) {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.save(pedido);
            t.commit();
            return true;
        }
    }

    @Override
    public Boolean actualizarPedido(Pedido pedido) {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.update(pedido);
            t.commit();
            return true;
        }
    }

    @Override
    public Pedido obtenerPedido(Integer id) {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Pedido.class, id);
        }
    }

    @Override
    public Boolean eliminarPedido(Pedido pedido) {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.delete(pedido);
            t.commit();
            return true;
        }
    }

    @Override
    public List<Pedido> obtenerListadoPedidos() {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            var q = s.createQuery("FROM Pedido");
            return q.list();
        }
    }

    @Override
    public List<Pedido> obtenerPedidosPendientesHoy() {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            var q = s.createQuery("FROM Pedido as ped WHERE ped.fecha = current_date() AND ped.estado = 'Pendiente'");
            return q.list();
        }
    }

}
