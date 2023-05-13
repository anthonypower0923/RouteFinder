module com.example.routefinder {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires opencsv;
    requires org.jgrapht.core;

    opens com.example.routefinder to javafx.fxml;
    exports com.example.routefinder;
}