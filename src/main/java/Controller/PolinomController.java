package Controller;

import Exceptions.InvalidInputException;
import Model.Monom;
import Model.Operatie;
import Model.Polinom;
import View.PolinomView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolinomController implements ActionListener {
    private final PolinomView view;
    private final Operatie model;

    public PolinomController(PolinomView view, Operatie model) {
        this.view = view;
        this.model = model;
        view.setVisible(true);
        this.view.addCalculateListener(this);
    }

    public Operatie getModel() {
        return model;
    }

    public PolinomView getView() {
        return view;
    }

    public static Polinom validateInput(String polynomial) throws InvalidInputException {
        String regex = "([+-]?\\d*x\\^\\d+)\\b|([+-]?\\d*x)\\b|([-+]?\\d+)|(\\bx{2,}\\b)";
        polynomial = polynomial.replaceAll("\\s+", "");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(polynomial);
        Polinom polinom = new Polinom();
        String verificare = "";
        while (matcher.find()) {
            if (matcher.group().equals(matcher.group(4)))
                throw new InvalidInputException("Inputul introdus de utilizator este invalid");
            String term = matcher.group();

            term = term.replaceAll("\\s+", "");
            try {
                polinom.addMonomToPolinom(parseStringToMonom(term, matcher));
            } catch (InvalidInputException err) {
                System.out.println(err.getMessage());
            }
            verificare = verificare + term;
        }
        if (!verificare.equals(polynomial)) {
            throw new InvalidInputException("Inputul introdus de utlizator este invalid");
        }
        return polinom;
    }

    private static Monom parseStringToMonom(String polynomial, Matcher matcher) throws InvalidInputException {
        double coeficient = 0;
        int power = 0;
        Monom monom = new Monom(coeficient, power, "x");
        if (polynomial.equals("1") || polynomial.equals("+1"))
            return new Monom(1, 0, "x");
        if (polynomial.equals("-1"))
            return new Monom(-1, 0, "x");
        if (polynomial.equals("+x") || polynomial.equals("x")) {
            return new Monom(1, 1, "x");
        } else if (polynomial.equals("-x")) {
            return new Monom(-1, 1, "x");
        }
        if (polynomial.equals(matcher.group(1))) {
            String[] parts = polynomial.split("x\\^");
            System.out.println(Arrays.toString(parts));
            if (parts.length == 2) {
                if (parts[0].equals("") || parts[0].equals("1") || parts[0].equals("+")) {
                    coeficient = 1;
                    power = Integer.parseInt(parts[1]);
                } else if (parts[0].equals("-1") || parts[0].equals("-")) {
                    coeficient = -1;
                    power = Integer.parseInt(parts[1]);
                } else {
                    coeficient = Double.parseDouble(parts[0]);
                    power = Integer.parseInt(parts[1]);
                }
            }
            return new Monom(coeficient, power, "x");
        } else if (polynomial.equals(matcher.group(2))) {
            String[] parts = polynomial.split("x");
            if (parts.length == 1) {
                coeficient = Double.parseDouble(parts[0]);
                power = 1;
                return new Monom(coeficient, power, "x");
            }
        } else {
            coeficient = Double.parseDouble(polynomial);
            return new Monom(coeficient, power, "x");
        }
        throw new InvalidInputException("Eroare cand se incearca convertirea din string in monom");
    }

    public static String parsePolinomToString(Polinom polinom) {
        StringBuilder buffer = new StringBuilder();
        if (polinom.getPolinom().isEmpty()) {
            buffer.append("0");
            return buffer.toString();
        }
        for (Map.Entry<Integer, Monom> entry : polinom.getPolinom().entrySet()) {
            double coeficient = entry.getValue().getCoeficient();
            int power = entry.getValue().getPower();
            if (coeficient > 0)
                buffer.append("+");
            if(power == 0) {
                buffer.append(entry.getValue().getCoeficient());
            }
            if(coeficient != 1 && coeficient != -1 && power > 0) {
            buffer.append(entry.getValue().getCoeficient());
            }
            if(coeficient == -1)
                buffer.append("-");
            if (power > 0) {
                buffer.append(entry.getValue().getVariable());
            }
            if (power > 1) {
                buffer.append("^").append(entry.getValue().getPower());
            }
        }
        String polinomString = buffer.toString();
        polinomString = polinomString.replace(".0", "");
        return polinomString;
    }

    public void actionPerformed(ActionEvent e) {
        String input1 = view.getInput1();
        String input2 = view.getInput2();
        String operator = view.getOperator();

        Polinom polinom1 = new Polinom();
        Polinom polinom2 = new Polinom();
        if(!Objects.equals(operator, "Derivation") && !Objects.equals(operator, "Integration")){
            try {
                polinom1 = validateInput(input1);
                polinom2 = validateInput(input2);
            } catch (InvalidInputException ex) {
                throw new RuntimeException(ex);
            }
        }
        else {
            try {
                polinom1 = validateInput(input1);
            } catch (InvalidInputException ex) {
                throw new RuntimeException(ex);
            }

        }
        switch (operator) {
            case "+" -> {
                    view.getResultField().setText(PolinomController.parsePolinomToString(Operatie.addition(polinom1, polinom2)));
            }
            case "-" -> {
                    view.getResultField().setText(PolinomController.parsePolinomToString(Operatie.substraction(polinom1, polinom2)));
            }
            case "*" -> {
                    view.getResultField().setText(PolinomController.parsePolinomToString(Operatie.multiplication(polinom1, polinom2)));
            }
            case "/" -> {
                    Polinom[] result = Operatie.division(polinom1, polinom2);
                    view.getResultField().setText(PolinomController.parsePolinomToString(result[0]));
                    view.getRemainderField().setText(PolinomController.parsePolinomToString(result[1]));
            }
            case "Derivation" -> {
                    view.getResultField().setText(PolinomController.parsePolinomToString(Operatie.derivation(polinom1)));
            }
            case "Integration" -> {
                    view.getResultField().setText(PolinomController.parsePolinomToString(Operatie.integration(polinom1)));
            }
        }
    }
}