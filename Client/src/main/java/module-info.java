module com.huehnerschulte.raffael.hostablechat {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires okhttp3;
    requires java.json;
    requires java.prefs;

    opens com.huehnerschulte.raffael.hostablechat to javafx.fxml;
    exports com.huehnerschulte.raffael.hostablechat;
    exports com.huehnerschulte.raffael.hostablechat.controller;
    opens com.huehnerschulte.raffael.hostablechat.controller to javafx.fxml;
}