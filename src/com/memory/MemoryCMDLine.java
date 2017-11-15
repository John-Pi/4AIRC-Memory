package com.memory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MemoryCMDLine {

    private final Scanner sc;
    private Paquet paquet;
    private ArrayList<Carte> listeCartes;
    private int maxX;
    private int maxY;
    private int[] tab;


    public MemoryCMDLine() {
        sc = new Scanner(System.in);

    }

    private void AffichageJeu() {


        System.out.print("       ");
        for (int i = 0; i <= maxY; i++) {
            System.out.print("   " + i + "   ");
        }
        int k = 0;
        for (Carte carte : listeCartes) {
            if (carte.x == k) {
                System.out.print("\n");

                System.out.print("   " + k + "   ");
                k++;
            }
            if (carte.isEtat()) {
                if (carte.getValeurCarte().length() > 1 ) {
                    System.out.print("  " + carte.getValeurCarte() + "   ");
                } else {
                    System.out.print("   " + carte.getValeurCarte() + "   ");
                }
            } else {
                System.out.print("   " + "X" + "   ");
            }

        }

    }


    public void setPaquet(Paquet paquet) {
        this.paquet = paquet;
        listeCartes = paquet.getList();
        tab  = paquet.getMatrixMaxs();
        maxY = tab[1];
        maxX = tab[0];
    }

    private void afficheIntro() {
        System.out.println("==========================================" + "\n"
                + "|       BIENVENUE DANS LE MEMORY          |" + "\n"
                + "==========================================");
        System.out.println("VOUS DEVEZ SELECTIONNER DEUX CARTES DE LA MËME VALEUR" + "\n"
                + "VOUS GAGNEZ QUAND IL N'Y A PLUS DE CARTES DANS LE JEUX"+"\n");
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
        System.out.println("Veuillez selectionner la coordonée X :");
        coord[1] = sc.nextInt();
        System.out.println("Veuillez selectionner la coordonée Y :");
        coord[0] = sc.nextInt();
        return coord;
    }

    public void afficheErreur(){
        System.out.println("Erreur");
    }




}