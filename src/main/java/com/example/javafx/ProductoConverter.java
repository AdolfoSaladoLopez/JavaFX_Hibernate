package com.example.javafx;

import javafx.util.StringConverter;
import models.Producto;

public class ProductoConverter extends StringConverter<Producto> {
    @Override
    public String toString(Producto producto) {
        return producto == null ? null : producto.getNombre();
    }

    @Override
    public Producto fromString(String s) {
        return null;
    }
}
