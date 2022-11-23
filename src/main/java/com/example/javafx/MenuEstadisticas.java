package com.example.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Producto;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuEstadisticas implements Initializable {
    ProductoDAO gestorProductos = new ProductoDAOHib();
    PedidoDAO gestorPedidos = new PedidoDAOHib();

    @FXML
    private TableColumn<Producto, String> cNombre;

    @FXML
    private Label lbProductoMasVendido;

    @FXML
    private Label nombreMejorCliente;

    @FXML
    private Label totalBeneficios;

    @FXML
    private TableView<Producto> tabla;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        actualizarTabla();

        lbProductoMasVendido.setText(gestorProductos.obtenerProductoMasVendido().get(0).toString());
        totalBeneficios.setText(gestorPedidos.obtenerCantidadTotal().get(0).toString());
        nombreMejorCliente.setText(gestorPedidos.obtenerMejorCliente().get(0).toString());


    }

    @FXML
    void volverInicio(ActionEvent event) {
        try {
            HelloApplication.setRoot("menu-inicio");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void actualizarTabla() {
        ObservableList<Producto> productosNoVendidos = FXCollections.observableArrayList();
        var listadoProductosNoVendidos = new ArrayList<>(gestorProductos.obtenerProductosVendidos());
        productosNoVendidos.addAll(listadoProductosNoVendidos);

        System.out.println(listadoProductosNoVendidos);

        tabla.getItems().clear();
        tabla.getItems().addAll(productosNoVendidos);


    }
}

