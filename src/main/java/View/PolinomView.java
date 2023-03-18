package View;
import Controller.PolinomController;
import Exceptions.InvalidInputException;
import Model.Operatie;
import Model.Polinom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PolinomView extends JFrame {
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
    public String getInput1(){
        return  inputField1.getText();
    }
    public String getInput2(){
        return  inputField2.getText();
    }
    public String getOperator(){
        return (String) operatorComboBox.getSelectedItem();
    }
    public void addCalculateListener(ActionListener listener) {
        calculateButton.addActionListener(listener);
    }

    public JTextField getResultField() {
        return resultField;
    }

    public JTextField getRemainderField() {
        return remainderField;
    }
}
