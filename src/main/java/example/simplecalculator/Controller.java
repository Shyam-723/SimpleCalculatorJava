package example.simplecalculator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class Controller {
    @FXML
    private AnchorPane myScene;

    @FXML
    private TextField outputTextField;

    @FXML
    private TextField historyTextField;

    private String currNumberString = "", lastStoredFunction = "";
    private int calculatedNumber = 0;
    private int storedValue = 0;
    private boolean isBinary = false;


    @FXML
    private void onKeyReleased(KeyEvent keyEvent) {
        String key = keyEvent.getText();

        if(keyEvent.getCode().isDigitKey()){
            updateTextField(key);
        }
        else{
            switch (key){
                case "+": binaryFunction("+");
                break;
                case "-": binaryFunction("-");
                break;
                case "*": binaryFunction("*");
                break;
                case "/": binaryFunction("/");
                break;
                case "=": calculateNumber(lastStoredFunction);
                break;
                case "c": buttonResetClick();
                break;
                case "C": buttonResetClick();
                break;
            }
        }
    }

    private void calculateNumber(String binaryFunction) {
        if (!lastStoredFunction.isEmpty()) {
            storedValue = updateCurrNumber();

            switch (lastStoredFunction) {
                case "+":
                    calculatedNumber += storedValue;
                    break;
                case "-":
                    calculatedNumber -= storedValue;
                    break;
                case "*":
                    calculatedNumber *= storedValue;
                    break;
                case "/":
                    if (storedValue != 0) {
                        calculatedNumber /= storedValue;
                    } else {
                        outputTextField.setText("Error");
                        return;
                    }
                    break;
            }

            outputTextField.setText(String.valueOf(calculatedNumber));
            // Reset for the next operation
            currNumberString = "";
            lastStoredFunction = binaryFunction.equals("=") ? "" : binaryFunction;
        } else {
            // First operation case
            calculatedNumber = updateCurrNumber();
            lastStoredFunction = binaryFunction.equals("=") ? "" : binaryFunction;
        }
    }

    private void updateTextField(String value) {
        if(value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/")) {
            outputTextField.setText(value);
            historyTextField.appendText(" " + value + " ");
            isBinary = true;
        }
        else if(isBinary){
            isBinary = false;
            outputTextField.setText(value);
            historyTextField.appendText(value);
        }
        else{
            outputTextField.appendText(value);
            historyTextField.appendText(value);
        }
    }

    private int updateCurrNumber() {
        currNumberString = outputTextField.getText();
        return Integer.parseInt(currNumberString);
    }

    private void binaryFunction(String func) {
        if (!lastStoredFunction.isEmpty() && !currNumberString.isEmpty()) {
            calculateNumber(lastStoredFunction);
        }
        else{
            calculatedNumber = updateCurrNumber();
        }
        lastStoredFunction = func;
        updateTextField(func);
    }

    @FXML
    private void buttonEqualClick() {
        calculateNumber(lastStoredFunction);
        lastStoredFunction = "";  // Reset after calculation
    }


    @FXML void buttonResetClick() {
        outputTextField.setText("");
        historyTextField.setText("");
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
}