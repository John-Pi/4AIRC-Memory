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
            System.out.println("Carte n°" + i + " Coordonées x" + carte.x + "y" + carte.y + " value = " + carte.valeurCarte + " etat = " + carte.etat);
            i++;
        }
    }

    public Carte findCarte(int x, int y) {

        for (Carte carte : listeCartes) {
            if (carte.x == x && carte.y == y) return carte;
        }
        return null;
    }

    public ArrayList<Carte> getList() {
        return listeCartes;
    }

    public int[] getMatrixMaxs() {
        int maxX = 0;
        int maxY = 0;
        for (Carte carte : listeCartes) {
            if (carte.x > maxX) maxX = carte.x;
            if (carte.y > maxY) maxY = carte.y;
        }
        int tab[] = new int[2];
        tab[0] = maxX;
        tab[1] = maxY;
        return tab;

    }
}
