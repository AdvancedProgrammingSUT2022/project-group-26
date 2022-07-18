module com.example.project.views {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive com.google.gson;
    requires xstream;

    exports com.example.project.models to com.google.gson, xstream;
    exports com.example.project.controllers to com.google.gson, xstream;

    opens com.example.project.models to com.google.gson, xstream;
    opens com.example.project.controllers to com.google.gson, xstream;


    opens com.example.project.views to javafx.fxml;
    exports com.example.project.views;
}