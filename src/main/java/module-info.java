module example.simplecalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens example.simplecalculator to javafx.fxml;
    exports example.simplecalculator;
}