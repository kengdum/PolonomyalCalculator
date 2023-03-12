package View;
import Controller.PolinomController;
import Exceptions.InvalidInputException;
import Model.Operatie;
import Model.Polinom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PolinomView extends JFrame implements ActionListener {
    private JLabel inputLabel1, inputLabel2, resultLabel, operatorLabel;
    private JTextField inputField1, inputField2, resultField;
    private JComboBox operatorComboBox;
    private JButton calculateButton;

    private static final String[] OPERATORS = {"+", "-", "*", "/", "Derivation"};

    public PolinomView() {
        setTitle("Calculator Polinoame");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the input labels
        inputLabel1 = new JLabel("Input 1:");
        inputLabel2 = new JLabel("Input 2:");

        // Create the input fields
        inputField1 = new JTextField(5);
        inputField2 = new JTextField(5);

        // Create the operator label and combo box
        operatorLabel = new JLabel("Operatie:");
        operatorComboBox = new JComboBox(OPERATORS);

        // Create the result label and field
        resultLabel = new JLabel("Rezultat:");
        resultField = new JTextField(10);
        resultField.setEditable(false);

        // Create the calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);

        // Add the components to the frame
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(inputLabel1);
        panel.add(inputField1);
        panel.add(inputLabel2);
        panel.add(inputField2);
        panel.add(operatorLabel);
        panel.add(operatorComboBox);
        panel.add(resultLabel);
        panel.add(resultField);
        add(panel, BorderLayout.CENTER);
        add(calculateButton, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        String input1 = inputField1.getText();
        String input2 = inputField2.getText();
        String operator = (String) operatorComboBox.getSelectedItem();

        // Perform the selected arithmetic operation and display the result
        switch (operator) {
            case "+":
                try {
                    Polinom polinom1 = new Polinom();
                    Polinom polinom2 = new Polinom();
                    polinom1 = PolinomController.validateInput(input1);
                    polinom2 = PolinomController.validateInput(input2);
                    resultField.setText(PolinomController.parsePolinomToString(Operatie.addition(polinom1,polinom2)));
                } catch (InvalidInputException err) {
                   System.out.println(err.getMessage());
                }
                break;
            case "-":
                try {
                    Polinom polinom1 = new Polinom();
                    Polinom polinom2 = new Polinom();
                    polinom1 = PolinomController.validateInput(input1);
                    polinom2 = PolinomController.validateInput(input2);
                    resultField.setText(PolinomController.parsePolinomToString(Operatie.substraction(polinom1,polinom2)));
                } catch (InvalidInputException err) {
                    System.out.println(err.getMessage());
                }
                break;
            case "*":
                try {
                    Polinom polinom1 = new Polinom();
                    Polinom polinom2 = new Polinom();
                    polinom1 = PolinomController.validateInput(input1);
                    polinom2 = PolinomController.validateInput(input2);
                    resultField.setText(PolinomController.parsePolinomToString(Operatie.multiplication(polinom1,polinom2)));
                }catch (InvalidInputException err){
                    System.out.println(err.getMessage());
                }
                break;
            case "/":
                //resultField.setText(Double.toString(input1 / input2));
                break;
            case "Derivation":
                try {
                    Polinom polinom1 = new Polinom();
                    polinom1 = PolinomController.validateInput(input1);
                    resultField.setText(PolinomController.parsePolinomToString(Operatie.derivation(polinom1)));
                } catch (InvalidInputException err) {
                    System.out.println(err.getMessage());
                }
                break;
        }
    }
}
