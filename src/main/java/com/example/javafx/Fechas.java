package com.example.javafx;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Fechas implements Initializable {

    @FXML
    private DatePicker dpPrimera;

    @FXML
    private DatePicker dtSegundo;

    LocalDate fechaUno = null;
    LocalDate fechaDos = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


    public static void showReport(LocalDate fechaUno, LocalDate fechaDos) throws JRException {
        Date primeraFecha, segundaFecha;

        primeraFecha = Date.valueOf(fechaUno);
        segundaFecha = Date.valueOf(fechaDos);


        HashMap hm = new HashMap();

        hm.put("FechaUno", primeraFecha);
        hm.put("FechaDos", segundaFecha);

        String report = "Fechas.jasper";

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                report,
                hm,
                JdbcUtil.getConnection()
        );

        JRViewer viewer = new JRViewer(jasperPrint);

        JFrame frame = new JFrame("Listado de pedidos");
        frame.getContentPane().add(viewer);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);

        System.out.print("Done!");
    }

    public static void pdfReport(LocalDate fechaUno, LocalDate fechaDos) throws JRException, ClassNotFoundException, SQLException {

        Date primeraFecha, segundaFecha;

        primeraFecha = Date.valueOf(fechaUno);
        segundaFecha = Date.valueOf(fechaDos);

        HashMap hm = new HashMap();
        hm.put("FechaUno", primeraFecha);
        hm.put("FechaDos", segundaFecha);

        String report = "Fechas.jasper";

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                report,
                hm,
                JdbcUtil.getConnection()
        );

        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("fechas.pdf"));
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exp.setConfiguration(conf);
        exp.exportReport();

        System.out.print("Done!");
    }

    public void btnVerReporte(javafx.event.ActionEvent actionEvent) {
        fechaUno = dpPrimera.getValue();
        fechaDos = dtSegundo.getValue();

        try {
            showReport(fechaUno, fechaDos);
            pdfReport(fechaUno, fechaDos);
        } catch (JRException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
