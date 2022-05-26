module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.example.project to javafx.fxml;
    exports com.example.project;
}