package com.memory;

public class Carte {
    public int x;
    public int y;
    public boolean etat = false;
    public String valeurCarte;

    public Carte(int x, int y, String valeurCarte) {
        this.x = x;
        this.y = y;
        this.valeurCarte = valeurCarte;
    }

    public void RetournerCarte() {
        if (etat == false) {
            etat = true;
        } else {
            etat = false;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public String getValeurCarte() {
        return valeurCarte;
    }

    public void setValeurCarte(String valeurCarte) {
        this.valeurCarte = valeurCarte;
    }
}



