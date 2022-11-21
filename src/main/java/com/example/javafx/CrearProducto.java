package com.example.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.Producto;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CrearProducto implements Initializable {
    ProductoDAO gestorProductos = new ProductoDAOHib();
    Producto nuevoProducto = new Producto();

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnEnviar;

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
        ObservableList<String> tipos = FXCollections.observableArrayList(
                "Bocadillo", "Bebida", "Snack"
        );

        ObservableList<Float> precios = FXCollections.observableArrayList(
                0.50f, 1.0f, 1.50f, 2.0f, 2.50f, 3.0f, 3.50f
        );

        ObservableList<String> disponibilidad = FXCollections.observableArrayList(
                "Disponible", "No disponible"
        );

        cbTipo.setItems(tipos);
        cbTipo.getSelectionModel().selectFirst();
        cbPrecio.setItems(precios);
        cbPrecio.getSelectionModel().selectFirst();
        cbDisponibilidad.setItems(disponibilidad);
        cbDisponibilidad.getSelectionModel().selectFirst();

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
        var cartaProductos = new ArrayList<>(gestorProductos.obtenerListadoProductos());
        for (Producto producto : cartaProductos) {
            if (producto.getNombre().equals(tfNombre.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Producto encontrado.");
                alert.setContentText("Ya existe un producto con ese nombre.");
                alert.showAndWait();
                return;
            }
        }

        nuevoProducto.setNombre(tfNombre.getText());
        nuevoProducto.setTipo(cbTipo.getSelectionModel().getSelectedItem());
        nuevoProducto.setPrecio(cbPrecio.getValue());
        nuevoProducto.setDisponibilidad(cbDisponibilidad.getSelectionModel().getSelectedItem().equals("Disponible"));

        gestorProductos.crearProducto(nuevoProducto);

        HelloApplication.setRoot("producto-inicio");
    }

}