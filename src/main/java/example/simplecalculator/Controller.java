package example.simplecalculator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField outputTextField;

    private String currNumberString = "";
    private int calculatedNumber = 0;
    private boolean isBinaryFunction = false;

    private int UpdateNumber() {
        currNumberString = outputTextField.getText();
        return Integer.parseInt(currNumberString);
    }

    @FXML void buttonAddClick() {
        UpdateNumber();
        outputTextField.setText("+");
        isBinaryFunction = true;
    }

    @FXML void buttonSubClick() {
        UpdateNumber();
        outputTextField.setText("-");
        isBinaryFunction = true;
    }

    @FXML void buttonMulClick() {
        UpdateNumber();
        outputTextField.setText("*");
        isBinaryFunction = true;
    }

    @FXML void buttonDivClick() {
        UpdateNumber();
        outputTextField.setText("/");
        isBinaryFunction = true;
    }

    @FXML void buttonOneClick() { updateTextField("1"); }
    @FXML void buttonTwoClick() { updateTextField("2"); }
    @FXML void buttonThreeClick() { updateTextField("3"); }
    @FXML void buttonFourClick() { updateTextField("4"); }
    @FXML void buttonFiveClick() { updateTextField("5"); }
    @FXML void buttonSixClick() { updateTextField("6"); }
    @FXML void buttonSevenClick() { updateTextField("7"); }
    @FXML void buttonEightClick() { updateTextField("8"); }
    @FXML void buttonNineClick() { updateTextField("9"); }
    @FXML void buttonZeroClick() { updateTextField("0"); }

    private void updateTextField(String value) {
        if (!isBinaryFunction) {
            outputTextField.appendText(value);
        } else {
            outputTextField.setText(value);
            isBinaryFunction = false;
        }
    }
}