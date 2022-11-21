package com.example.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Pedido;
import models.PedidoString;
import models.Producto;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductoInicio implements Initializable {
    ProductoDAO gestorProductos = new ProductoDAOHib();


    @FXML
    private Menu btnAbout;

    @FXML
    private Button btnCrearProducto;

    @FXML
    private Button btnEliminarProducto;

    @FXML
    private Button btnModificarProducto;

    @FXML
    private Label lAbajo;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, String> tcDisponibilidad;

    @FXML
    private TableColumn<Producto, String> tcId;

    @FXML
    private TableColumn<Producto, String> tcNombre;

    @FXML
    private TableColumn<Producto, String> tcPrecio;

    @FXML
    private TableColumn<Producto, String> tcTipo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tcDisponibilidad.setCellValueFactory(new PropertyValueFactory<>("disponibilidad"));

        actualizarTabla();
    }

    private void actualizarTabla() {
        var cartaProductos = new ArrayList<>(gestorProductos.obtenerListadoProductos());

        ObservableList<Producto> datos = FXCollections.observableArrayList();
        datos.addAll(cartaProductos);

        tablaProductos.getItems().clear();
        tablaProductos.getItems().addAll(cartaProductos);

    }
    @FXML
    void crearPedido(ActionEvent event) {

    }

    @FXML
    void crearProducto(ActionEvent event) {
        try {
            HelloApplication.setRoot("crear-producto");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void eliminarProducto(ActionEvent event) {

    }

    @FXML
    void modificarProducto(ActionEvent event) {

    }


}
