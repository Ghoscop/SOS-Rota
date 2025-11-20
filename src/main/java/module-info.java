module com.example.sosrota {
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
    requires javafx.graphics;
    requires java.desktop;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;

    // Pacote principal
    exports com.example.sosrota;
    opens com.example.sosrota to javafx.fxml;

    // Controllers
    exports com.example.sosrota.Controller;
    opens com.example.sosrota.Controller to javafx.fxml;

    // >>> ESSENCIAL: seu Application está aqui! <<<
    exports com.example.sosrota.View;
    opens com.example.sosrota.View to javafx.fxml, javafx.graphics;
}
