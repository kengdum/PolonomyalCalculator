package View;
import Controller.PolinomController;
import Exceptions.InvalidInputException;
import Model.Operatie;
import Model.Polinom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PolinomView extends JFrame implements ActionListener {
    private JLabel inputLabel1, inputLabel2, resultLabel, operatorLabel, remainderLabel;
    private JTextField inputField1, inputField2, resultField, remainderField;
    private JComboBox operatorComboBox;
    private JButton calculateButton;

    private static final String[] OPERATORS = {"+", "-", "*", "/", "Derivation", "Integration"};

    public PolinomView() {
        setTitle("Calculator Polinoame");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        inputLabel1 = new JLabel("Input 1:");
        inputLabel2 = new JLabel("Input 2:");
        inputLabel1.setHorizontalAlignment(JLabel.CENTER);
        inputLabel2.setHorizontalAlignment(JLabel.CENTER);

        inputField1 = new JTextField(5);
        inputField2 = new JTextField(5);

        operatorLabel = new JLabel("Operatie:");
        operatorComboBox = new JComboBox(OPERATORS);
        operatorLabel.setHorizontalAlignment(JLabel.CENTER);

        resultLabel = new JLabel("Rezultat:");
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        resultField = new JTextField(10);
        resultField.setEditable(false);

        remainderLabel = new JLabel("Rest:");
        remainderLabel.setHorizontalAlignment(JLabel.CENTER);
        remainderField = new JTextField(10);
        remainderField.setEditable(false);



        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);

        // Add the components to the frame
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(inputLabel1);
        panel.add(inputField1);
        panel.add(inputLabel2);
        panel.add(inputField2);
        panel.add(operatorLabel);
        panel.add(operatorComboBox);
        panel.add(resultLabel);
        panel.add(resultField);
        panel.add(remainderLabel);
        panel.add(remainderField);
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
                    System.out.println(Operatie.addition(polinom1,polinom2));
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
                try {
                    Polinom polinom1 = new Polinom();
                    Polinom polinom2 = new Polinom();
                    polinom1 = PolinomController.validateInput(input1);
                    polinom2 = PolinomController.validateInput(input2);
                    Polinom[] result = Operatie.division(polinom1,polinom2);
                    resultField.setText(PolinomController.parsePolinomToString(result[0]));
                    remainderField.setText(PolinomController.parsePolinomToString(result[1]));
                }catch (InvalidInputException err){
                    System.out.println(err.getMessage());

                }
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
            case "Integration":
                try {
                    Polinom polinom1 = new Polinom();
                    polinom1 = PolinomController.validateInput(input1);
                    resultField.setText(PolinomController.parsePolinomToString(Operatie.integration(polinom1)));
                } catch (InvalidInputException err) {
                    System.out.println(err.getMessage());
                }
                break;
        }
    }
}
