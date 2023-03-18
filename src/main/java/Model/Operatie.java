package Model;

import Exceptions.InvalidInputException;

import javax.management.modelmbean.ModelMBeanOperationInfo;
import java.util.Map;

public class Operatie {
    public static Polinom addition(Polinom polinom1, Polinom polinom2) {
        Polinom polinomAdunare = new Polinom(polinom1);

        for (Map.Entry<Integer, Monom> entry : polinom2.getPolinom().entrySet()) {
            if (polinom1.getPolinom().containsKey(entry.getKey())) {
                double var1 = polinom1.getPolinom().get(entry.getKey()).getCoeficient();
                double var2 = polinom2.getPolinom().get(entry.getKey()).getCoeficient();
                if (var1 + var2 != 0) {
                    polinomAdunare.getPolinom().get(entry.getKey()).setCoeficient(var1 + var2);
                } else {
                    polinomAdunare.getPolinom().remove(entry.getKey());
                }

            } else {
                Monom monom = new Monom(entry.getValue().getCoeficient(), entry.getKey(), entry.getValue().getVariable());
                polinomAdunare.addMonomToPolinom(monom);
            }
        }
        polinomAdunare.checkZeros();
        polinomAdunare.formatDecimals();
        return polinomAdunare;
    }

    public static Polinom substraction(Polinom polinom1, Polinom polinom2) {
        Polinom polinomScadere = new Polinom(polinom1);

        for (Map.Entry<Integer, Monom> entry : polinom2.getPolinom().entrySet()) {
            if (polinom1.getPolinom().containsKey(entry.getKey())) {
                double var1 = polinom1.getPolinom().get(entry.getKey()).getCoeficient();
                double var2 = polinom2.getPolinom().get(entry.getKey()).getCoeficient();
                if (var1 - var2 != 0) {
                    polinomScadere.getPolinom().get(entry.getKey()).setCoeficient(var1 - var2);
                } else {
                    polinomScadere.getPolinom().remove(entry.getKey());
                }
            } else {
                Monom monom = new Monom(entry.getValue().getCoeficient() * -1, entry.getKey(), entry.getValue().getVariable());
                polinomScadere.addMonomToPolinom(monom);
            }
        }
        polinomScadere.checkZeros();
        polinomScadere.formatDecimals();
        return polinomScadere;

    }
    public static Polinom multiplication(Polinom polinom1, Polinom polinom2) {
        Polinom polinomMultiplication = new Polinom();
        for (Map.Entry<Integer, Monom> entry1 : polinom1.getPolinom().entrySet()) {
            for (Map.Entry<Integer, Monom> entry2 : polinom2.getPolinom().entrySet()) {
                double coeficient = entry1.getValue().getCoeficient() * entry2.getValue().getCoeficient();
                int putere = entry1.getValue().getPower() + entry2.getValue().getPower();
                polinomMultiplication.addMonomToPolinom(new Monom(coeficient, putere, "x"));
            }
        }
        polinomMultiplication.checkZeros();
        polinomMultiplication.formatDecimals();
        return polinomMultiplication;
    }
    public static Polinom derivation(Polinom polinom) {
        Polinom polinomDerivare = new Polinom();

        for(Map.Entry<Integer,Monom> entry : polinom.getPolinom().entrySet()) {
            if(entry.getValue().getPower() == 1) {
                polinomDerivare.addMonomToPolinom(new Monom(entry.getValue().getCoeficient(), 0 , "x"));
            }
            else if (entry.getValue().getPower() != 0) {
                polinomDerivare.addMonomToPolinom(new Monom(entry.getValue().getCoeficient() * entry.getValue().getPower(), entry.getValue().getPower() -1, "x"));
            }
        }
        polinomDerivare.checkZeros();
        polinomDerivare.formatDecimals();
        return polinomDerivare;
    }
    public static Polinom integration(Polinom polinom){
        Polinom polinomIntegrare = new Polinom();
        for(Map.Entry<Integer,Monom> entry : polinom.getPolinom().entrySet()){
            double coeficient = entry.getValue().getCoeficient() / (entry.getValue().getPower() + 1);
            int power = entry.getValue().getPower() + 1;
            polinomIntegrare.addMonomToPolinom(new Monom(coeficient,power,"x"));
        }
        polinomIntegrare.checkZeros();
        polinomIntegrare.formatDecimals();
        return  polinomIntegrare;
     }
    public static Polinom[] division(Polinom polinom1, Polinom polinom2) {
        Polinom quotient  = new Polinom();
        Polinom remainder = new Polinom(polinom1);
        while (!remainder.getPolinom().isEmpty() && remainder.getHighestPower()>= polinom2.getHighestPower()) {
            double coeficient = remainder.getPolinom().get(remainder.getHighestPower()).getCoeficient() /
                    polinom2.getPolinom().get(polinom2.getHighestPower()).getCoeficient();
            int putere = remainder.getHighestPower() - polinom2.getHighestPower();
            Polinom tempPolinom = new Polinom();
            Monom term = new Monom(coeficient,putere,"x");
            tempPolinom.addMonomToPolinom(term);
            quotient .addMonomToPolinom(term);
            Polinom tempPolinomMultiplication = multiplication(tempPolinom, polinom2);
            remainder = substraction(remainder, tempPolinomMultiplication);

        }
        quotient.checkZeros();
        quotient.formatDecimals();
        remainder.checkZeros();
        remainder.formatDecimals();
        Polinom[] result = {quotient , remainder};
        System.out.println(quotient);
        System.out.println(remainder);
        return result;
    }

}
