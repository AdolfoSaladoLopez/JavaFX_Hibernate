package com.example.javafx;

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
}
