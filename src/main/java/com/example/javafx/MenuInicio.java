package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuInicio implements Initializable {
    PedidoDAO gestorPedidos = new PedidoDAOHib();
    ProductoDAO gestorProductos = new ProductoDAOHib();


    private VBox vb = new VBox();
    @FXML
    private Button btnEstadisticas;

    @FXML
    private Button btnGestionarPedidos;

    @FXML
    private Button btnGestionarProductos;

    @FXML
    private ImageView ivDesayunos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            var fis = new FileInputStream("src/main/resources/images/breakfast.png");
            Image avatar = new Image(fis);
            ivDesayunos.setImage(avatar);

        } catch (FileNotFoundException ex) {
            System.out.println("El usuario no tiene imagen");
        }

        System.out.println(gestorPedidos.obtenerCantidadTotal());
        System.out.println(gestorPedidos.obtenerMejorCliente());
        System.out.println(gestorProductos.obtenerProductoMasVendido().get(0));
        System.out.println(gestorProductos.obtenerProductosNoVendidos());

    }

    @FXML
    void gestionarPedidos(ActionEvent event) {
        try {
            HelloApplication.setRoot("inicio");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void gestionarProductos(ActionEvent event) {
        try {
            HelloApplication.setRoot("producto-inicio");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void mostrarEstadisticas(ActionEvent event) {
        try {
            HelloApplication.setRoot("menu-estadisticas");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
