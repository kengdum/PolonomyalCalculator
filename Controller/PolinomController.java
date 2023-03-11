package Controller;

import Exceptions.InvalidInputException;
import Model.Monom;
import Model.Polinom;
import View.PolinomView;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolinomController {
    private PolinomView view;
    private Polinom model;

    public PolinomController(PolinomView view, Polinom model) {
        this.view = view;
        this.model = model;
    }

    public Polinom getModel() {
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
            System.out.println(term);

            term = term.replaceAll("\\s+", "");
            try {
                polinom.addMonomToPolinom(parseStringToMonom(term, matcher));
            } catch (InvalidInputException err) {
                System.out.println(err.getMessage());
            }
            verificare = verificare + term;
        }
        System.out.println(polynomial);
        System.out.println(verificare);
        if (!verificare.equals(polynomial)) {
            throw new InvalidInputException("Inputul introdus de utlizator este invalid");
        }
        return polinom;
    }

    private static Monom parseStringToMonom(String polynomial, Matcher matcher) throws InvalidInputException {
        int coeficient = 0;
        int power = 0;
        if (polynomial.equals("x")) {
            return new Monom(1, 1, "x");
        }
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
        } else {
            coeficient = Integer.parseInt(polynomial);
            return new Monom(coeficient, power, "x");
        }
        throw new InvalidInputException("Eroare cand se incearca convertirea din string in monom");
    }

    public static String parsePolinomToString(Polinom polinom) {
        StringBuilder buffer = new StringBuilder();
        for (Map.Entry<Integer, Monom> entry : polinom.getPolinom().entrySet()) {
            if (entry.getValue().getCoeficient() > 1) {
                if(entry.getValue().getPower() > 0) {
                    buffer.append("+").append(entry.getValue().getCoeficient()).append(entry.getValue().getVariable());
                }
                else {
                    buffer.append("+").append(entry.getValue().getCoeficient());
                }
            } else if (entry.getValue().getCoeficient() < 1) {
                if (entry.getValue().getCoeficient() != -1) {
                    buffer.append(entry.getValue().getCoeficient()).append(entry.getValue().getVariable());
                } else {
                    buffer.append("-").append(entry.getValue().getVariable());
                }

            } else if (entry.getValue().getCoeficient() == 1 && entry.getValue().getPower() > 0) {
                buffer.append("+").append(entry.getValue().getVariable());
            }
            if (entry.getValue().getPower() > 1) {
                buffer.append("^").append(entry.getValue().getPower());
            }
        }
        return buffer.toString();
    }

}
