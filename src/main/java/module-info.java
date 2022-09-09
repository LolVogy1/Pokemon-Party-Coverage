module com.example.pokemontypecoverage2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.pokemontypecoverage2 to javafx.fxml;
    exports com.example.pokemontypecoverage2;
}