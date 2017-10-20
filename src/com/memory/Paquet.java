package com.memory;

import java.util.ArrayList;
import java.util.Collections;

public class Paquet {

    private static ArrayList<Carte> listeCartes = new ArrayList<Carte>();

    public Paquet() {

        ConstruirePaquet();
        AssignerValeur();
    }

    public void RetounerCarte(Carte carte) {


    }

    private void ConstruirePaquet() {
        int nombreCartePaquet = 24;
        int nbColonne;
        int nbLines;

        for (nbLines = 0; nbLines < 4; nbLines++) {
            for (nbColonne = 0; nbColonne < 6; nbColonne++) {
                listeCartes.add(new Carte(nbLines, nbColonne, "noValue"));
            }
        }
    }

    private void AssignerValeur() {
        ArrayList<String> tableVal = new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            tableVal.add(Integer.toString(i));
            tableVal.add(Integer.toString(i));
        }
        Collections.shuffle(tableVal);
        System.out.println(tableVal.toString());
        int k = 0;
        for (Carte carte : listeCartes) {
            carte.setValeurCarte(tableVal.get(k));
            k++;
        }
    }

    public void affichepaquetTEST() {
        int i = 1;
        for (Carte carte : listeCartes) {
            System.out.println("Carte n°" + i + " Coordonées x" + carte.x + "y" + carte.y + " value = " + carte.valeurCarte);
            i++;
        }
    }

    public Carte findCarte(int x, int y) {

        for (Carte carte : listeCartes) {
            if (carte.x == x && carte.y == y) return carte;
        }
        return null;
    }

}
