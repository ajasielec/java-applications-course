module com.example.fxtutorial {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.fxtutorial to javafx.fxml;
    exports com.example.fxtutorial;
}