module Client {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive com.google.gson;
    requires xstream;

    exports com.example.project.models.Tile to com.google.gson, xstream;
    exports com.example.project.models.Technology to com.google.gson, xstream;
    exports com.example.project.models.Building to com.google.gson, xstream;
    exports com.example.project.models.Units to com.google.gson, xstream;
    exports com.example.project.models.Feature to com.google.gson, xstream;
    exports com.example.project.models.GlobalChat to com.google.gson, xstream;
    exports com.example.project.models.Improvement to com.google.gson, xstream;
    exports com.example.project.models.Resource to com.google.gson, xstream;
    exports com.example.project.models.Units.Combat to com.google.gson, xstream;
    exports com.example.project.models.Units.Nonecombat to com.google.gson, xstream;

    exports com.example.project.models to com.google.gson, xstream;
//    exports com.example.project.controllers to com.google.gson, xstream;

    opens com.example.project.models.Tile to com.google.gson, xstream;
    opens com.example.project.models.Technology to com.google.gson, xstream;
    opens com.example.project.models.Building to com.google.gson, xstream;
    opens com.example.project.models.Units to com.google.gson, xstream;
    opens com.example.project.models.Feature to com.google.gson, xstream;
    opens com.example.project.models.GlobalChat to com.google.gson, xstream;
    opens com.example.project.models.Improvement to com.google.gson, xstream;
    opens com.example.project.models.Resource to com.google.gson, xstream;
    opens com.example.project.models.Units.Combat to com.google.gson, xstream;
    opens com.example.project.models.Units.Nonecombat to com.google.gson, xstream;

    opens com.example.project.models to com.google.gson, xstream;
//    opens com.example.project.controllers to com.google.gson, xstream;

    opens com.example.project.views to javafx.fxml;
    exports com.example.project.views;
}