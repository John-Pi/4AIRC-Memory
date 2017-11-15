package com.memory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MemoryCMDLine {

    private Scanner sc;
    private Paquet paquet;
    private ArrayList<Carte> listeCartes;
    private int maxX;
    private int maxY;
    private int[] tab;


    public MemoryCMDLine() {
        sc = new Scanner(System.in);

    }

    public void affichageJeu() {


        System.out.print("  Y\\X |");
        for (int i = 0; i <= maxY; i++) {
            System.out.print("   " + i + "   ");
        }
        System.out.println();
        for (int i = 0; i <= maxY; i++) {
            System.out.print("--------");
        }
        int k = 0;
        for (Carte carte : listeCartes) {
            if (carte.x == k) {
                System.out.print("\n");

                System.out.print("   " + k + "  |");
                k++;
            }
            if (carte.isEtat()) {
                if (carte.getValeurCarte().length() > 1) {
                    System.out.print("  " + carte.getValeurCarte() + "   ");
                } else {
                    System.out.print("   " + carte.getValeurCarte() + "   ");
                }
            } else {
                System.out.print("   " + "X" + "   ");
            }

        }
        System.out.print("\n");
        System.out.print("\n");
    }


    public void setPaquet(Paquet paquet) {
        this.paquet = paquet;
        listeCartes = paquet.getList();
        tab = paquet.getMatrixMaxs();
        maxY = tab[1];
        maxX = tab[0];
    }

    private void afficheIntro() {
        System.out.println("==========================================" + "\n"
                + "|       BIENVENUE DANS LE MEMORY          |" + "\n"
                + "==========================================");
        System.out.println("VOUS DEVEZ SELECTIONNER DEUX CARTES DE LA MÊME VALEUR" + "\n"
                + "VOUS GAGNEZ QUAND IL N'Y A PLUS DE CARTES DANS LE JEUX" + "\n");
    }

    private void afficheMenu() {
        System.out.println("APPUYER SUR ENTREE POUR JOUER");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void afficheStart() {
        afficheIntro();
        afficheMenu();

    }

    public int[] retournerCarte() {
        int[] coord = new int[2];
        boolean wrongtype = false;
        System.out.print("VEUILLEZ SELECTIONNER LA COORDONEE X : ");
        do {
                if(sc.hasNextInt()){
                    coord[1] = sc.nextInt();
                    wrongtype = false;
                }else {
                    System.out.println("VEUILLEZ SAISIR UN CHIFFRE !");
                    System.out.print("VEUILLEZ SELECTIONNER LA COORDONEE X : ");
                    sc.next();
                    wrongtype = true;
                }
        } while (wrongtype);
        System.out.print("VEUILLEZ SELECTIONNER LA COORDONEE Y : ");
        do {
            if(sc.hasNextInt()){
                coord[0] = sc.nextInt();
                wrongtype = false;
            }else {
                System.out.println("VEUILLEZ SAISIR UN CHIFFRE !");
                System.out.print("VEUILLEZ SELECTIONNER LA COORDONEE Y : ");
                sc.next();
                wrongtype = true;
            }
        } while (wrongtype);
        System.out.println();
        return coord;
    }

    public void afficheErreur() {
        System.out.println("Erreur");
    }


    public void coupJoue(int coupRestant, int paireRestantes, boolean reussite) {
        if (reussite) {
            System.out.print("BRAVO ! : COUP GAGNE");
        } else {
            System.out.print("DOMMAGE ! : COUP PERDU \n");
        }
        System.out.println("IL VOUS RESTE : " + coupRestant + " COUPS POUR TROUVER " + paireRestantes + " PAIRES \n");
    }
}