package com.memory;

public class ControleurJeu {

    private JeuMemory jeuMemory;
    private Paquet paquet = null;
    private MemoryCMDLine memoryCMDLine;
    private int state;
    private Carte carte1 = null, carte2 = null;
    private boolean Rejouer = false;
    Carte tempcarte;


    public ControleurJeu() {
        jeuMemory = new JeuMemory();
        RecupPaquet();
        state = State.startTry;
    }

    public void RecupPaquet() {
        if (paquet == null) paquet = jeuMemory.getPaquetCartes();
    }


    public int RetournerCarte(int x, int y) {

        switch (state) {

            case State.startTry:
                tempcarte = jeuMemory.FindCarte(x, y);
                if (jeuMemory.IsValide(tempcarte) == State.CarteValide) {
                    jeuMemory.RetournerCarte(tempcarte);
                    state = State.firstCardChoosed;
                    carte1 = tempcarte;
                }
                break;
            case State.firstCardChoosed:
                tempcarte = jeuMemory.FindCarte(x, y);
                if (jeuMemory.IsValide(tempcarte) == State.CarteValide) {
                    jeuMemory.RetournerCarte(tempcarte);
                    carte2 = tempcarte;
                    if (jeuMemory.SontPaires(carte1, carte2)) {
                        state = State.startTry;
                        jeuMemory.paireTrouvee(carte1, carte2);
                        carte1 = null;
                        carte2 = null;
                        if (jeuMemory.getPairesRestantes() == 0){
                            return State.victory;
                        }else {
                            return State.coupGagnant;
                        }
                    } else {
                        state = State.startTry;
                        jeuMemory.RetournerCarte(carte1);
                        jeuMemory.RetournerCarte(carte2);
                        carte1 = null;
                        carte2 = null;
                        if (jeuMemory.getCoupRestants() == 0){
                            return State.defeat;
                        }else {
                            return State.coupPerdant;
                        }

                    }
                }
                break;

        }
        return state;
    }

    public void LauchGameCMD() {

        do {
            jeuMemory = new JeuMemory();
            RecupPaquet();
            memoryCMDLine = new MemoryCMDLine();
            memoryCMDLine.setPaquet(paquet);
            memoryCMDLine.afficheStart();
            Carte tempcarte;
            state = State.startTry;
            int[] tab;

            while (jeuMemory.getPairesRestantes() > 0 && jeuMemory.getCoupRestants() > 0) {

                switch (state) {
                    case State.startTry:
                        memoryCMDLine.affichageJeu();
                        tab = memoryCMDLine.retournerCarte();
                        tempcarte = jeuMemory.FindCarte(tab[0], tab[1]);
                        if (jeuMemory.IsValide(tempcarte) == State.CarteValide) {
                            jeuMemory.RetournerCarte(tempcarte);
                            state = State.firstCardChoosed;
                            carte1 = tempcarte;
                        } else {
                            memoryCMDLine.afficheErreur();
                        }
                        break;
                    case State.firstCardChoosed:
                        memoryCMDLine.affichageJeu();
                        tab = memoryCMDLine.retournerCarte();
                        tempcarte = jeuMemory.FindCarte(tab[0], tab[1]);
                        if (jeuMemory.IsValide(tempcarte) == State.CarteValide) {
                            jeuMemory.RetournerCarte(tempcarte);
                            state = State.pairControl;
                            carte2 = tempcarte;
                        } else {
                            memoryCMDLine.afficheErreur();
                        }
                        break;
                    case State.pairControl:
                        if (jeuMemory.SontPaires(carte1, carte2)) {
                            state = State.coupGagnant;
                        } else {
                            state = State.coupPerdant;
                        }
                        break;
                    case State.coupGagnant:
                        memoryCMDLine.affichageJeu();
                        jeuMemory.paireTrouvee(carte1, carte2);
                        memoryCMDLine.coupJoue(jeuMemory.getCoupRestants(), jeuMemory.getPairesRestantes(), true);
                        state = State.startTry;
                        break;
                    case State.coupPerdant:
                        memoryCMDLine.affichageJeu();
                        jeuMemory.RetournerCarte(carte1);
                        jeuMemory.RetournerCarte(carte2);
                        carte1 = null;
                        carte2 = null;
                        state = State.startTry;
                        memoryCMDLine.coupJoue(jeuMemory.getCoupRestants(), jeuMemory.getPairesRestantes(), false);
                        break;
                }
            }
            if (jeuMemory.getPairesRestantes() == 0 && jeuMemory.getCoupRestants() > 0) {
                memoryCMDLine.partieGagnee();
                Rejouer = memoryCMDLine.rejouer();
            } else {
                memoryCMDLine.partiePerdue();
                Rejouer = memoryCMDLine.rejouer();
            }
        } while (Rejouer);
        memoryCMDLine.messageFin();
    }

    public Paquet getPaquet() {
        return paquet;
    }
    public int isValide(int x,int y){
        return jeuMemory.IsValide(jeuMemory.FindCarte(x,y));
    }
}
