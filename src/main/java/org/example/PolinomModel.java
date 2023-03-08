package org.example;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class PolinomModel {

    public TreeMap<Integer, Monom> polinom;

    public PolinomModel(){
        polinom = new TreeMap<>();
    }
    public void addMonomToPolinom(Monom monom) {
        polinom.put(monom.getPower(), monom);
    }
    public TreeMap<Integer, Monom> getPolinom() {
        return polinom;
    }

    public static PolinomModel addition(PolinomModel polinom1, PolinomModel polinom2) {
        ArrayList<Integer> puteri = new ArrayList<>();
        PolinomModel polinomAdunare = new PolinomModel();

        for(Map.Entry<Integer, Monom> entry : polinom1.getPolinom().entrySet()) {
            puteri.add(entry.getKey());
            if(polinom2.getPolinom().containsKey(entry.getKey()) == true) {
                int var1 = polinom1.getPolinom().get(entry.getKey()).getCoeficient();
                int var2 = polinom2.getPolinom().get(entry.getKey()).getCoeficient();

                Monom monom = new Monom(var1 + var2, entry.getKey(), entry.getValue().getVariable());
                polinomAdunare.addMonomToPolinom(monom);
            }
            else {
                Monom monom = new Monom(entry.getValue().getCoeficient(), entry.getKey(), entry.getValue().getVariable());
                polinomAdunare.addMonomToPolinom(monom);
            }
        }
        for (Map.Entry<Integer, Monom> entry : polinom2.getPolinom().entrySet()) {
            if(!puteri.contains(entry.getKey())) {
                puteri.add(entry.getKey());
                Monom monom = new Monom(entry.getValue().getCoeficient(), entry.getKey(), entry.getValue().getVariable());
                polinomAdunare.addMonomToPolinom(monom);
            }
        }
        return polinomAdunare;
    }
    public static PolinomModel substraction(PolinomModel polinom1, PolinomModel polinom2) {
        ArrayList<Integer> puteri = new ArrayList<>();
        PolinomModel polinomScadere = new PolinomModel();

        for(Map.Entry<Integer, Monom> entry : polinom1.getPolinom().entrySet()) {
            puteri.add(entry.getKey());
            if(polinom2.getPolinom().containsKey(entry.getKey()) == true) {
                int var1 = polinom1.getPolinom().get(entry.getKey()).getCoeficient();
                int var2 = polinom2.getPolinom().get(entry.getKey()).getCoeficient();

                Monom monom = new Monom(var1 - var2, entry.getKey(), entry.getValue().getVariable());
                polinomScadere.addMonomToPolinom(monom);
            }
            else {
                Monom monom = new Monom(entry.getValue().getCoeficient(), entry.getKey(), entry.getValue().getVariable());
                polinomScadere.addMonomToPolinom(monom);
            }
        }
        for (Map.Entry<Integer, Monom> entry : polinom2.getPolinom().entrySet()) {
            if(!puteri.contains(entry.getKey())) {
                puteri.add(entry.getKey());
                Monom monom = new Monom(-entry.getValue().getCoeficient(), entry.getKey(), entry.getValue().getVariable());
                polinomScadere.addMonomToPolinom(monom);
            }
        }
        return polinomScadere;
    }

    @Override
    public String toString() {
        String buffer = new String();
        for(Map.Entry<Integer, Monom> entry : polinom.entrySet()) {
            buffer = buffer + entry.getValue().toString() + "\n";
        }
        return buffer;
    }
}
