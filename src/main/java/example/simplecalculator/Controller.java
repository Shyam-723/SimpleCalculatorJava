package example.simplecalculator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField outputTextField;

    @FXML
    void buttonOneClick() {
        outputTextField.appendText("1");
    }

    @FXML
    void buttonTwoClick() {
        outputTextField.appendText("2");
    }

    @FXML
    void buttonThreeClick() {
        outputTextField.appendText("3");
    }

    @FXML
    void buttonFourClick() {
        outputTextField.appendText("4");
    }

    @FXML
    void buttonFiveClick() {
        outputTextField.appendText("5");
    }

    @FXML
    void buttonSixClick() {
        outputTextField.appendText("6");
    }

    @FXML
    void buttonSevenClick() {
        outputTextField.appendText("7");
    }

    @FXML
    void buttonEightClick() {
        outputTextField.appendText("8");
    }

    @FXML
    void buttonNineClick() {
        outputTextField.appendText("9");
    }

    @FXML
    void buttonZeroClick() {
        outputTextField.appendText("0");
    }

}