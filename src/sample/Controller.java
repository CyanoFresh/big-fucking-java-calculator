package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Controller {
    // Internal fields
    private int a;
    private int b;
    private boolean lastBtnOperator = false;
    private boolean fillingB = false;
    private String operator = "+";

    private int memory;

    // FX fields
    public TextField display;
    public Pane pane;

    public void buttonBackSpaceClick(ActionEvent actionEvent) {
        String string = Integer.toString(a);

        string = string.substring(0, string.length() - 1);

        a = Integer.parseInt(string);

        display.setText(string);

        log();
    }

    public void buttonDigitClick(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        String buttonNumber = button.getText();

        Integer newInt;

        if (fillingB) {
            newInt = addDigit(b, buttonNumber);
            b = newInt;
        } else {
            newInt = addDigit(a, buttonNumber);
            a = newInt;
        }

        lastBtnOperator = false;

        updateDisplay(newInt);

        log();
    }

    public void buttonClearClick(ActionEvent actionEvent) {
        a = 0;
        b = 0;
        fillingB = false;
        lastBtnOperator = false;

        updateDisplay(0);

        log();
    }

    private int addDigit(int source, int digit) {
        String string = Integer.toString(source);

        string += digit;

        return Integer.parseInt(string);
    }

    private int addDigit(int source, String digit) {
        String string = Integer.toString(source);

        string += digit;

        return Integer.parseInt(string);
    }

    private void updateDisplay(String newText) {
        display.setText(newText);
    }

    private void updateDisplay(double newText) {
        display.setText(Double.toString(newText));
    }

    private void updateDisplay(Integer newText) {
        display.setText(Integer.toString(newText));
    }

    private void log() {
        System.out.println();
        System.out.println("a is " + a);
        System.out.println("b is " + b);
        System.out.println("memory is " + memory);
        System.out.println("fillingB is " + (fillingB ? "true" : "false"));
        System.out.println("lastBtnOperator is " + (lastBtnOperator ? "true" : "false"));
        System.out.println("operator is " + operator);
    }

    public void buttonSqrtClick(ActionEvent actionEvent) {
        double result = Math.sqrt((double) a);

        updateDisplay(result);

        a = (int)result;

        log();
    }

    public void buttonOperatorClick(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();

        operator = button.getText();
        fillingB = true;
        lastBtnOperator = true;

        log();
    }

    public void buttonEnterClick(ActionEvent actionEvent) {
        if (lastBtnOperator) {
            return;
        }

        double result = 0;

        switch (operator) {
            case "/":
                if (b == 0) {
                    result = 0;
                    break;
                }

                result = a / b;

                break;
            case "*":
                result = a * b;
                break;
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
        }

        a = (int)result;
        b = 0;
        lastBtnOperator = false;
        fillingB = false;

        updateDisplay(Double.toString(result));

        log();
    }

    public void buttonSwastClick(ActionEvent actionEvent) {
        updateDisplay("Hail Hitler!");
        pane.setStyle("-fx-background-color: red;");
    }
}
