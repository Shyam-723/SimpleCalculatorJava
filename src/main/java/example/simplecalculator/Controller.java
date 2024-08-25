package example.simplecalculator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Controller {
    @FXML
    private AnchorPane myScene;

    @FXML
    private TextField outputTextField;

    @FXML
    private TextField historyTextField;

    @FXML
    public void initialize() {
        // Make the outputTextField and historyTextField non-editable
        outputTextField.setEditable(false);
        historyTextField.setEditable(false);
    }

    private String currNumberString = "", lastStoredFunction = "";
    private BigDecimal calculatedNumber = BigDecimal.ZERO;
    private BigDecimal storedValue = BigDecimal.ZERO;
    private boolean isBinary = false;
    private boolean errorShown = false;


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
                    calculatedNumber = calculatedNumber.add(storedValue).setScale(5, RoundingMode.HALF_UP).stripTrailingZeros();
                    break;
                case "-":
                    calculatedNumber = calculatedNumber.subtract(storedValue).setScale(5, RoundingMode.HALF_UP).stripTrailingZeros();
                    break;
                case "*":
                    calculatedNumber = calculatedNumber.multiply(storedValue).setScale(5, RoundingMode.HALF_UP).stripTrailingZeros();
                    break;
                case "/":
                    if (storedValue.compareTo(BigDecimal.ZERO) != 0) {
                        calculatedNumber = calculatedNumber.divide(storedValue).setScale(5, RoundingMode.HALF_UP).stripTrailingZeros();
                    } else {
                        showError();
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
        if(errorShown){
            errorShown = false;
            buttonResetClick();
        } else if(value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/")) {
            outputTextField.setText(value);
            historyTextField.appendText(" " + value + " ");
            isBinary = true;
        } else if(isBinary){
            isBinary = false;
            outputTextField.setText(value);
            historyTextField.appendText(value);
        } else{
            outputTextField.appendText(value);
            historyTextField.appendText(value);
        }
    }

    private BigDecimal updateCurrNumber() {
        currNumberString = outputTextField.getText();
        return new BigDecimal(currNumberString);
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

    private void showError(){
        outputTextField.setText("Error");
        errorShown = true;
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
        calculatedNumber = BigDecimal.ZERO;
        storedValue = BigDecimal.ZERO;
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