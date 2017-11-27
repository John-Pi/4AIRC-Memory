package com.memory;

public class ControleurJeu {

    private JeuMemory jeuMemory;
    private Paquet paquet = null;
    private MemoryCMDLine memoryCMDLine;
    private int state;
    private Carte carte1 = null, carte2 = null;
    private boolean Rejouer = false;

    public ControleurJeu() {
    }

    public void RecupPaquet() {
        if (paquet == null) paquet = jeuMemory.getPaquetCartes();
    }


    public void RetournerCarte(int x, int y) {
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

}
