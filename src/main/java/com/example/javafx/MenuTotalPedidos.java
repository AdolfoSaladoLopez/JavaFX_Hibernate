package com.example.javafx;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Pedido;
import models.PedidoString;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuTotalPedidos implements Initializable {

    PedidoDAO gestorPedidos = new PedidoDAOHib();

    @FXML
    private Menu btnAbout;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private Label lAbajo;

    @FXML
    private TableView<PedidoString> tabla;

    @FXML
    private TableColumn<PedidoString, String> tcCliente;

    @FXML
    private TableColumn<PedidoString, String> tcEstado;

    @FXML
    private TableColumn<PedidoString, String> tcFecha;

    @FXML
    private TableColumn<PedidoString, String> tcId;

    @FXML
    private TableColumn<PedidoString, String> tcProducto;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcId.setCellValueFactory(new PropertyValueFactory<PedidoString, String>("id"));
        tcFecha.setCellValueFactory(new PropertyValueFactory<PedidoString, String>("fecha"));
        tcCliente.setCellValueFactory(new PropertyValueFactory<PedidoString, String>("cliente"));
        tcEstado.setCellValueFactory(new PropertyValueFactory<PedidoString, String>("estado"));
        tcProducto.setCellValueFactory(new PropertyValueFactory<PedidoString, String>("producto"));


        actualizarTabla();

    }

    private void actualizarTabla() {
        var pedidosPendientes = new ArrayList<Pedido>(gestorPedidos.obtenerTotalPedidosOrdenados());
        var pedidosString = new ArrayList<PedidoString>();

        for (Pedido pedido : pedidosPendientes) {
            PedidoString ps = new PedidoString();

            ps.setId(pedido.getId().toString());
            ps.setFecha(pedido.getFecha().toString());
            ps.setCliente(pedido.getCliente());
            ps.setEstado(pedido.getEstado());
            ps.setProducto(pedido.getProducto().getNombre());

            pedidosString.add(ps);
        }


        ObservableList<PedidoString> datos = FXCollections.observableArrayList();
        datos.addAll(pedidosString);

        tabla.getItems().clear();
        tabla.getItems().addAll(datos);

    }

    @FXML
    void crearPedido(ActionEvent event) {
        try {
            HelloApplication.setRoot("crear-pedido");
        } catch (IOException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void eliminarPedido(ActionEvent event) throws IOException {
        if (tabla.getSelectionModel().getSelectedItem() != null) {
            Integer id = Integer.valueOf(tabla.getSelectionModel().getSelectedItem().getId());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Eliminación del pedido");
            alert.setTitle("Confirmación");
            alert.setContentText("¿Estas seguro de confirmar la acción?");
            Optional<ButtonType> action = alert.showAndWait();
            // Si hemos pulsado en aceptar
            if (action.get() == ButtonType.OK) {
                gestorPedidos.eliminarPedido(gestorPedidos.obtenerPedido(id));
                actualizarTabla();
            }
        }
    }

    @FXML
    void modificarPedido(ActionEvent event) throws IOException {
        if (tabla.getSelectionModel().getSelectedItem() != null) {

            Integer id = Integer.valueOf(tabla.getSelectionModel().getSelectedItem().getId());
            SessionData.setPedido(gestorPedidos.obtenerPedido(id));
            HelloApplication.setRoot("modificar-pedido");
        }
    }

    @FXML
    void IrPedidosPendientes(ActionEvent event) {
        try {
            HelloApplication.setRoot("inicio");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void alertaInformacion(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Información del software.");
        alert.setContentText("Este software ha sido desarrollado por Adolfo Salado.\n" +
                "Realizado con Hibernate y JavaFX.");
        alert.showAndWait();
    }

    @FXML
    void irAInicio(ActionEvent event) {
        try {
            HelloApplication.setRoot("menu-inicio");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}