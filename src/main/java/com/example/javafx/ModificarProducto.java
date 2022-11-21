package com.example.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.Producto;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ModificarProducto implements Initializable {
    ProductoDAO gestorProductos = new ProductoDAOHib();
    Producto productoAModificar = new Producto();

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnEnviar;

    @FXML
    private Label lbDatosActuales;

    @FXML
    private ComboBox<String> cbDisponibilidad;

    @FXML
    private ComboBox<Float> cbPrecio;

    @FXML
    private ComboBox<String> cbTipo;

    @FXML
    private TextField tfNombre;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productoAModificar = SessionData.getProducto();

        ObservableList<String> tipos = FXCollections.observableArrayList(
                "Bocadillo", "Bebida", "Snack"
        );

        ObservableList<Float> precios = FXCollections.observableArrayList(
                0.50f, 1.0f, 1.50f, 2.0f, 2.50f, 3.0f, 3.50f
        );

        ObservableList<String> disponibilidad = FXCollections.observableArrayList(
                "Disponible", "No disponible"
        );

        tfNombre.setText(productoAModificar.getNombre());
        cbTipo.setItems(tipos);
        cbTipo.getSelectionModel().select(productoAModificar.getTipo());
        cbPrecio.setItems(precios);
        cbPrecio.getSelectionModel().select(productoAModificar.getPrecio());
        cbDisponibilidad.setItems(disponibilidad);
        cbDisponibilidad.getSelectionModel().selectFirst();

        lbDatosActuales.setText("Nombre actual: " + productoAModificar.getNombre() +
                " - Tipo actual: " + productoAModificar.getTipo() +
                " - Precio actual: " + productoAModificar.getPrecio() +
                " - Disponibilidad actual: " + (productoAModificar.getDisponibilidad()?"Disponible":"No disponible"));


    }

    @FXML
    void cancelar(ActionEvent event) {
        try {
            HelloApplication.setRoot("producto-inicio");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void enviar(ActionEvent event) throws IOException {
        var cartaProductos = new ArrayList<Producto>(gestorProductos.obtenerListadoProductos());
        for (Producto producto : cartaProductos) {
            if (producto.getNombre().equals(tfNombre.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Producto encontrado.");
                alert.setContentText("Ya existe un producto con ese nombre.");
                alert.showAndWait();
                return;
            }
        }

        productoAModificar.setNombre(tfNombre.getText());
        productoAModificar.setTipo(cbTipo.getSelectionModel().getSelectedItem());
        productoAModificar.setPrecio(cbPrecio.getValue());
        productoAModificar.setDisponibilidad(cbDisponibilidad.getSelectionModel().getSelectedItem().equals("Disponible"));

        gestorProductos.actualizarProducto(productoAModificar);

        HelloApplication.setRoot("producto-inicio");
    }




}
