package org.example;

import Model.Monom;
import Model.Operatie;
import Model.PolinomModel;
import View.PolinomView;

public class App
{
    public static void main( String[] args )
    {
//        Monom var1 = new Monom(4, 3, "x");
//        Monom var2 = new Monom(1, 2 , "x");
//        Monom var3 = new Monom(1,  0, "x");
//
//        Monom var4 = new Monom(1, 2, "x");
//        Monom var5 = new Monom(1, 1, "x");
//        Monom var6 = new Monom(2, 3, "x");
//
//        PolinomModel polinom = new PolinomModel();
//        PolinomModel polinom1 = new PolinomModel();
//
//        polinom.addMonomToPolinom(var1);
//        polinom.addMonomToPolinom(var2);
//        polinom.addMonomToPolinom(var3);
//
//        polinom1.addMonomToPolinom(var4);
//        polinom1.addMonomToPolinom(var5);
//        polinom1.addMonomToPolinom(var6);
//
//        PolinomModel polinom3 = new PolinomModel(polinom1);
//
//        System.out.println(polinom3);
//        System.out.println(polinom1);
//
//        System.out.println(Operatie.addition(polinom1,polinom3));
//        System.out.println(Operatie.substraction(polinom1,polinom3));
        PolinomView calculator = new PolinomView();
        calculator.setVisible(true);


    }
}
