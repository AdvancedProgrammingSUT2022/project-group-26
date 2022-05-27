module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.example.project.views to javafx.fxml;
    exports com.example.project.views;
}