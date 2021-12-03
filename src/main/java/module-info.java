module com.essohpee.stopwatch {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.jfoenix;

    opens com.essohpee.stopwatch to javafx.fxml;
    exports com.essohpee.stopwatch;
}