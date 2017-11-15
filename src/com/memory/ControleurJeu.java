package com.memory;

public class ControleurJeu {

    private JeuMemory jeuMemory;
    private Paquet paquet = null;
    private MemoryCMDLine memoryCMDLine;

    public ControleurJeu() {
    }

    public void RecupPaquet() {
        if (paquet == null) paquet = jeuMemory.getPaquetCartes();
    }


    public void RetournerCarte(int x, int y) {
        jeuMemory.RetournerCarte(x, y);
    }
    public void LauchGame(){
        jeuMemory = new JeuMemory();
        RecupPaquet();
        memoryCMDLine = new MemoryCMDLine();
        memoryCMDLine.setPaquet(paquet);
    }

}
