module org.example.tiendaaed {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires mysql.connector.j;
    requires java.sql;
    requires java.desktop;

    opens Views to javafx.fxml, javafx.graphics, org.junit.jupiter.api;
    opens Main to javafx.fxml, javafx.graphics,org.junit.jupiter.api;
    opens View to javafx.fxml, javafx.graphics,org.junit.jupiter.api;
    opens Class to javafx.base;

    exports Main;
    exports Views;


}