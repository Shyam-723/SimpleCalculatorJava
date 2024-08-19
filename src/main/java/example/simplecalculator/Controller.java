package example.simplecalculator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class Controller {
    @FXML
    private TextField outputTextField;

    @FXML
    private TextField historyTextField;

    private String currNumberString = "", lastStoredFunction = "";
    private int calculatedNumber = 0;
    private int storedValue = 0;
    private boolean isBinaryFunction = false;


    private void calculateNumber(String binaryFunction) {
        calculatedNumber = storedValue;
        storedValue = updateCurrNumber();

        switch(binaryFunction){
            case "+":
                calculatedNumber +=  storedValue;
                break;
            case "-":
                calculatedNumber -= storedValue;
                break;
            case "*":
                calculatedNumber *= storedValue;
                break;
            case "/":
                calculatedNumber /= storedValue;
                break;
        }

        outputTextField.setText(calculatedNumber + "");
    }

    private int updateCurrNumber() {
        currNumberString = outputTextField.getText();
        return Integer.parseInt(currNumberString);
    }

    @FXML void buttonEqualClick() {
        if(!lastStoredFunction.isEmpty()) {
            calculateNumber(lastStoredFunction);
        }
        else{
            if(!historyTextField.getText().isEmpty()) {
                storedValue = Integer.parseInt(outputTextField.getText());
            }
        }
    }

    private void binaryFunction(String func){
        buttonEqualClick();
        outputTextField.setText(func);
        historyTextField.appendText(" " + func + " ");
        isBinaryFunction = true;
        lastStoredFunction = func;
    }

    @FXML void buttonResetClick() {
        outputTextField.setText("");
        historyTextField.setText("");
        isBinaryFunction = false;
        lastStoredFunction = "";
        calculatedNumber = 0;
        storedValue = 0;
    }

    @FXML void buttonAddClick() { binaryFunction("+"); }
    @FXML void buttonSubClick() { binaryFunction("-"); }
    @FXML void buttonMulClick() { binaryFunction("*"); }
    @FXML void buttonDivClick() { binaryFunction("/"); }

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

        historyTextField.appendText(value);
    }
}