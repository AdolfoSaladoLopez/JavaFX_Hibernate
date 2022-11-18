package com.example.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import models.Pedido;
import models.Producto;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CrearPedido implements Initializable {
    ProductoDAO gestorProductos = new ProductoDAOHib();
    PedidoDAO gestorPedidos = new PedidoDAOHib();
    Pedido nuevoPedido = new Pedido();

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnEnviar;

    @FXML
    private ComboBox<Producto> cbProducto;

    @FXML
    private TextField tfNombre;

    @FXML
    void cancelar(ActionEvent event) throws IOException {
        HelloApplication.setRoot("inicio");
    }

    @FXML
    void enviar(ActionEvent event) throws IOException {
        nuevoPedido.setCliente(tfNombre.getText());
        nuevoPedido.setEstado("Pendiente");

        gestorPedidos.crearPedido(nuevoPedido);

        HelloApplication.setRoot("inicio");
    }

    @FXML
    void elementoSeleccionado(ActionEvent event) {
        Object evt = event.getSource();

        if (evt.equals(cbProducto)) {
            nuevoPedido.setProducto(cbProducto.getValue());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Producto> items = FXCollections.observableArrayList();
        ArrayList<Producto> listadoProductos = new ArrayList<>(gestorProductos.obtenerListadoProductos());
        items.addAll(listadoProductos);

        cbProducto.setItems(items);
        cbProducto.setConverter(new ProductoConverter());

    }
}
