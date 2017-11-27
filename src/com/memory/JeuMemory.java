package com.memory;

public class JeuMemory {

    private Paquet paquetCartes;
    private Carte carte1 = null, carte2 = null, carteF;
    private int coupsRestant, pairesTrouvees;

    public JeuMemory(){
        paquetCartes = new Paquet();
        coupsRestant = 2;
    }

    public Carte FindCarte(int x, int y){
        carteF = paquetCartes.findCarte(x,y);

        return carteF;
    }

    public boolean SontPaires(Carte carte1, Carte carte2){
        boolean res = false;
        if(carte1.getValeurCarte().equals(carte2.getValeurCarte())){
            res = true;
        }
        coupsRestant--;
        return res;

    }

    public void RetournerCarte(Carte carte){
        paquetCartes.RetournerCarte(carte);
    }

    public Paquet getPaquetCartes() {
        return paquetCartes;
    }

    public void AffichePaquetTest(){
        paquetCartes.affichepaquetTEST();
    }

    public int IsValide(Carte carte){
        if (carte == null) return State.errorHorsPaquet;
        if(carte.trouvee)return State.errorCarteTrouvee;
        if(carte.etat)return State.errorCarteDejaRet;

        return State.CarteValide;
    }

    public void paireTrouvee(Carte carte1, Carte carte2) {
        paquetCartes.paireTrouvee(carte1, carte2);
    }
    public int getPairesRestantes(){
        return paquetCartes.getPaireRestantes();
    }

    public int getCoupRestants() {
        return coupsRestant;
    }
}
