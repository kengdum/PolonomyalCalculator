package org.example;

import Controller.PolinomController;
import Model.Operatie;
import View.PolinomView;

public class App {
    public static void main(String[] args) {
        PolinomView calculator = new PolinomView();
        Operatie model = new Operatie();
        PolinomController controller = new PolinomController(calculator, model);
        controller.getView().setVisible(true);

    }
}
