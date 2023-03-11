package Controller;

import Exceptions.InvalidInputException;
import Model.Monom;
import Model.PolinomModel;
import View.PolinomView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolinomController {
    private PolinomView view;
    private PolinomModel model;

    public PolinomController(PolinomView view, PolinomModel model) {
        this.view = view;
        this.model = model;
    }

    public PolinomModel getModel() {
        return model;
    }

    public PolinomView getView() {
        return view;
    }
    public static PolinomModel validateInput(String polynomial) throws InvalidInputException {
        String regex = "([+-]?\\d*x\\^\\d+)\\b|([+-]?\\d*x)\\b|([-+]?\\d+)|(\\bx{2,}\\b)";
        polynomial = polynomial.replaceAll("\\s+", "");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(polynomial);
        PolinomModel polinom = new PolinomModel();
        String verificare = new String("");
        while (matcher.find()) {
            if(matcher.group().equals(matcher.group(4)))
                throw new InvalidInputException("Invalid input");
            String term = matcher.group();
            System.out.println(term);

            term = term.replaceAll("\\s+", "");
            polinom.addMonomToPolinom(parseStringToMonom(term,matcher));
            verificare = verificare + term;
        }
        System.out.println(polynomial);
        System.out.println(verificare);
        if (!verificare.equals(polynomial)) {
            throw  new InvalidInputException("Inputul introdus de utlizator este invalid");
        }
        return polinom;
    }
    private static Monom parseStringToMonom (String polynomial, Matcher matcher) {
        int coeficient = 0;
        int power = 0;
        if (polynomial.equals(matcher.group(1))) {
            String[] parts = polynomial.split("x\\^");
            if (parts.length == 2) {
                if (parts[0].equals("")) {
                    coeficient = 1;
                    power = Integer.parseInt(parts[1]);
                } else if (parts[0].equals("-1")) {
                    coeficient = -1;
                    power = Integer.parseInt(parts[1]);
                } else {
                    coeficient = Integer.parseInt(parts[0]);
                    power = Integer.parseInt(parts[1]);
                }
            }
            return new Monom(coeficient, power, "x");
        } else if (polynomial.equals(matcher.group(2))) {
            String[] parts = polynomial.split("x");
            if (parts.length == 1) {
                if (parts[0].equals("x")) {
                    coeficient = 1;
                    power = 1;
                } else if (parts[0].equals("-x")) {
                    coeficient = -1;
                    power = 1;
                } else {
                    coeficient = Integer.parseInt(parts[0]);
                    power = 1;
                }
                return new Monom(coeficient, power, "x");
            }
        }
        coeficient = Integer.parseInt(polynomial);
        power = 0;
        return new Monom(coeficient, power, "x");
    }

}
