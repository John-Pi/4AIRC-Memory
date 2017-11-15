package com.memory;

public class State {
    //CARTE VALIDE
    public static final int CarteValide = 0;

    //LES CARTES SONT LES MEMES
    public static final int SontPaires = 1;

    //LES CARTES NE SONT PAS LES MEMES
    public static final int errorNeSontPasPaires = 2;

    //La carte selectionée est Hors Du Paquet
    public static final int errorHorsPaquet = 3;

    //LA CARTE SELECTIONNEE A DEJA ETE RETOURNEE
    public static final int errorCarteDejaRet = 4;

    //CARTE NON DEJA TROUVEE
    public static final int errorCarteTrouvee = 5;

    //VICTOIRE
    public static final int victory = 6;

    //VICTOIRE
    public static final int defeat = 7;

    //PREMIERE CARTE CHOISI
    public static final int firstCardChoosed = 8;

    //DEBUT D'UN ESSAI
    public static final int startTry = 9;
    public static final int pairControl = 10;
    public static final int coupGagnant = 11;

    public static final int coupPerdant = 12;
}
