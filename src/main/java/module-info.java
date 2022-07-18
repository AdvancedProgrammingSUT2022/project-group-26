module com.example.project.views {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive com.google.gson;
    requires xstream;

    opens com.example.project.views to javafx.fxml;
    exports com.example.project.views;
}