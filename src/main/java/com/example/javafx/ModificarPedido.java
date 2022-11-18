package com.example.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Pedido;
import models.Producto;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ModificarPedido implements Initializable {
    ProductoDAO gestorProductos = new ProductoDAOHib();
    PedidoDAO gestorPedidos = new PedidoDAOHib();
    Pedido pedidoModificado = new Pedido();
    Pedido pedidoAModificar = new Pedido();

    private BorderPane bp;
    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnEnviar;

    @FXML
    private ComboBox<String> cbEstado;

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
        pedidoAModificar.setCliente(tfNombre.getText());

        gestorPedidos.actualizarPedido(pedidoAModificar);
        HelloApplication.setRoot("inicio");

    }

    @FXML
    void eventoSeleccionado(ActionEvent event) {
        Object evt = event.getSource();

        if (evt.equals(cbEstado)) {
            pedidoAModificar.setEstado(cbEstado.getValue());
        }
    }

    @FXML
    void productoSeleccionado(ActionEvent event) {
        Object evt = event.getSource();

        if (evt.equals(cbProducto)) {
            pedidoAModificar.setProducto(cbProducto.getValue());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pedidoAModificar = SessionData.getPedido();
        tfNombre.setText(pedidoAModificar.getCliente());


        ObservableList<String> items = FXCollections.observableArrayList(
                "Pendiente", "Recogido"
        );
        cbEstado.setItems(items);
        cbEstado.getSelectionModel().select(pedidoAModificar.getEstado());

        ObservableList<Producto> productos = FXCollections.observableArrayList();
        ArrayList<Producto> listadoProductos = new ArrayList<>(gestorProductos.obtenerListadoProductos());
        productos.addAll(listadoProductos);

        cbProducto.setItems(productos);
        cbProducto.setConverter(new ProductoConverter());
        cbProducto.getSelectionModel().select(pedidoAModificar.getProducto());
    }
}
