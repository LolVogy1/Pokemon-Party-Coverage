module com.example.pokemontypecoverage {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.pokemontypecoverage to javafx.fxml;
    exports com.example.pokemontypecoverage;
}