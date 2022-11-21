package com.example.javafx;

import jakarta.persistence.Query;
import models.Producto;
import org.hibernate.Transaction;

import java.util.List;

public class ProductoDAOHib implements ProductoDAO {
    @Override
    public Boolean crearProducto(Producto producto) {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.persist(producto);
            t.commit();
            return true;
        }
    }

    @Override
    public Boolean actualizarProducto(Producto producto) {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.update(producto);
            t.commit();
            return true;
        }
    }

    @Override
    public Producto obtenerProducto(Integer id) {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Producto.class, id);
        }
    }

    @Override
    public Boolean eliminarProducto(Producto producto) {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.delete(producto);
            t.commit();
            return true;
        }
    }

    @Override
    public List<Producto> obtenerListadoProductos() {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            var q = s.createQuery("FROM Producto");
            return q.list();
        }
    }

    @Override
    public List obtenerProductoMasVendido() {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            Query q = s.createQuery("select pro.nombre from Producto pro group by pro.nombre order by pro.nombre asc");
            return q.getResultList();
        }
    }

    @Override
    public List<Producto> obtenerProductosNoVendidos() {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            Query q = s.createQuery("select producto from Producto pro, Pedido ped where pro.id " +
                    "not in (select ped.producto.id from Pedido ped) group by producto");
            return q.getResultList();
        }
    }
}
