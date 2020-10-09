module SurebetsCalculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires unirest.java;
    requires com.google.gson;

    opens org.dman to javafx.fxml;
    exports org.dman;
}