package Controller;

import Model.Monom;
import Model.PolinomModel;
import View.PolinomView;

import java.sql.SQLOutput;
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
    public static void validateInput(String polynomial) {
        String regex = "([-+]?\\d*x\\^(\\d+))|([-+]?\\d*x)|([-+]?\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(polynomial);
        String verificare = new String("");
        while (matcher.find()) {
            String term = matcher.group();
            term = term.replaceAll("\\s+", "");
            verificare = verificare + term;
            System.out.println(term);
        }
        polynomial = polynomial.replaceAll("\\s+", "");
        System.out.println(verificare);
        System.out.println(polynomial);
        if (!verificare.equals(polynomial)) System.out.println("Input invalid");
    }


}
