module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires jasperreports;
    requires java.desktop;

    opens models;

    opens com.example.javafx to javafx.fxml, java.sql;
    exports com.example.javafx;
}