package com.memory;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Johnpi on 04/10/2017.
 */
public class memory {
    private static ArrayList<Carte> listeCartes;
    private static int i;
    private String carte00;
    private String carte01;
    private String carte10;
    private String carte11;
    private boolean replay = true;

    public memory() {

    }

    public void start() {
        listeCartes = new ArrayList<Carte>();
        createListe();
        game();

    }


    private void game() {
        Scanner sc = new Scanner(System.in);
        int x, y;
        System.out.println("Bienvenue dans le Memory");
        while (replay == true) {
            resetAffiche();
            afficheMatr();
            System.out.println("Choisir la ligne de la première carte : (0/1)");
            x = sc.nextInt();
            System.out.println("Choisir la colonne de la première carte : (0/1)");
            y = sc.nextInt();
            Carte tempCarte1 = findCartes(x, y);
            tempCarte1.returned();
            afficheMatr();
            Carte tempCarte2 = null;
            do {
                System.out.println("Choisir la ligne de la deuxieme carte : (0/1)");
                x = sc.nextInt();
                System.out.println("Choisir la colonne de la deuxieme carte : (0/1)");
                y = sc.nextInt();
                tempCarte2 = findCartes(x, y);
            } while (tempCarte1.X == tempCarte2.X && tempCarte1.Y == tempCarte2.Y);
            tempCarte2.returned();
            afficheMatr();
            if (tempCarte1.isSame(tempCarte2.screenValue)) {
                System.out.println("Vous avez gagné !!!!");
            } else {
                System.out.println("Ta perdu !!!!");
            }
            System.out.println("Voulez-vous rejouer ? : (0/1)");
            int replaySelect = sc.nextInt();
            if (replaySelect == 1) {
                replay = true;
            } else {
                replay = false;
            }
        }

    }

    private Carte findCartes(int x, int y) {
        for (Carte carte : listeCartes) {
            if ((carte.X == x) && (carte.Y == y)) {
                return carte;
            }
        }
        return null;
    }

    private void resetAffiche() {
        for (Carte carte : listeCartes) {
            carte.screenValue = "?";
        }
    }

    private static void createListe() {
        Carte temp = new Carte(0, 0, "A");
        listeCartes.add(temp);
        temp = new Carte(0, 1, "B");
        listeCartes.add(temp);
        temp = new Carte(1, 0, "A");
        listeCartes.add(temp);
        temp = new Carte(1, 1, "B");
        listeCartes.add(temp);
        /*temp = new Carte(0, 2, "c");
        listeCartes.add(temp);
        temp = new Carte(1, 2, "c");
        listeCartes.add(temp);*/
    }

    public void afficheMatr() {

        int size = listeCartes.size();
        int medium = size / 2;
        System.out.print("\n       ");
        for (int i = 0; i < medium; i++) {
            System.out.print("colonne" + i + " \t");
        }
        System.out.print("\n       ");
        for (int i = 0; i < medium; i++) {
            System.out.print("-------- \t");
        }
        System.out.print("\n");
        for (int i = 0; i < medium; i++) {
            System.out.print("ligne" + i + " \t");
            for (int j = 0; j < medium; j++) {
                for (Carte ma_carte : listeCartes) {
                    if (ma_carte.X == i && ma_carte.Y == j) {
                        System.out.print("      " + ma_carte.screenValue);
                    }
                }
            }
            System.out.println("");
        }

    }


}