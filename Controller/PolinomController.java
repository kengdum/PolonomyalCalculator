package Controller;

import Exceptions.InvalidInputException;
import Model.Monom;
import Model.PolinomModel;
import View.PolinomView;

import java.sql.SQLOutput;
import java.util.IllegalFormatException;
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
    public static boolean validateInput(String polynomial) throws InvalidInputException {
        String regex = "([+-]?\\d*x\\^\\d+)\\b|([+-]?\\d*x)\\b|([-+]?\\d+)|(\\bx{2,}\\b)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(polynomial);
        String verificare = new String("");
        while (matcher.find()) {
            if(matcher.group().equals(matcher.group(4)))
                throw new InvalidInputException("Invalid input");
            String term = matcher.group();
            System.out.println(term);

            term = term.replaceAll("\\s+", "");
            verificare = verificare + term;
        }
        polynomial = polynomial.replaceAll("\\s+", "");
        if (!verificare.equals(polynomial)) {
            throw  new InvalidInputException("Inputul introdus de utlizator este invalid");
        }
        return true;
    }

}
