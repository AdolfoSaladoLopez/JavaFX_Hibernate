package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reportes {

    @FXML
    void btnCarta(ActionEvent event) throws JRException {
        try {
            verReporteCarta();
            pdfCarta();
        } catch (JRException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnFechas(ActionEvent event) {
        try {
            HelloApplication.setRoot("fechas");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnPedidos(ActionEvent event) {
        try {
            verReportePendientes();
            pdfHoy();
        } catch (JRException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void verReporteCarta() throws JRException {

        HashMap hm = new HashMap();


        String report = "carta.jasper";

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                report,
                hm,
                JdbcUtil.getConnection()
        );

        JRViewer viewer = new JRViewer(jasperPrint);

        JFrame frame = new JFrame("Listado de productos");
        frame.getContentPane().add(viewer);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);

        System.out.print("Done!");
    }

    public static void pdfCarta() throws JRException, ClassNotFoundException, SQLException {

        HashMap hm = new HashMap();


        String report = "carta.jasper";

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                report,
                hm,
                JdbcUtil.getConnection()
        );

        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("carta.pdf"));
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exp.setConfiguration(conf);
        exp.exportReport();

        System.out.print("Done!");
    }

    public static void verReportePendientes() throws JRException {

        HashMap hm = new HashMap();


        String report = "Pedidos_hoy.jasper";

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                report,
                hm,
                JdbcUtil.getConnection()
        );

        JRViewer viewer = new JRViewer(jasperPrint);

        JFrame frame = new JFrame("Listado de pedidos hoy");
        frame.getContentPane().add(viewer);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);

        System.out.print("Done!");
    }

    public static void pdfHoy() throws JRException, ClassNotFoundException, SQLException {

        HashMap hm = new HashMap();


        String report = "Pedidos_hoy.jasper";

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                report,
                hm,
                JdbcUtil.getConnection()
        );

        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("Pedidos_hoy.pdf"));
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exp.setConfiguration(conf);
        exp.exportReport();

        System.out.print("Done!");
    }

}
