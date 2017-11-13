package com.memory;

public class JeuMemory {

    private Paquet paquetCartes;
    private Carte carte1, carte2, carteF;
    private int coupsRestant, pairesTrouvees;

    public JeuMemory(){

    }

    public Carte FindCarte(int x, int y){
        carteF = paquetCartes.findCarte(x,y);
        return carteF;
    }

    public boolean SontPaires(Carte carte1, Carte carte2){
        if(carte1.getValeurCarte() == carte2.getValeurCarte()){
            boolean res = true;
        }
        else{
            res = false;
        }
        return res;
    }

    public void Traitement(boolean resPaire){

    }

    public void RetournerCarte(int x, int y){

    }

    public Paquet getPaquetCartes() {
        return paquetCartes;
    }
}
