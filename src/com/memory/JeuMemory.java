package com.memory;

public class JeuMemory {

    private Paquet paquetCartes;
    private Carte carte1 = null, carte2 = null, carteF;
    private int coupsRestant, pairesTrouvees;

    public JeuMemory(){
        paquetCartes = new Paquet();
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
        return res;
    }

    public void RetournerCarte(Carte carte){
        carte.RetournerCarte();
    }

    public Paquet getPaquetCartes() {
        return paquetCartes;
    }

    public void AffichePaquetTest(){
        paquetCartes.affichepaquetTEST();
    }

    public int IsValide(Carte carte){
        if(carte.trouvee == true)return State.errorCarteTrouvee;
        if(carte.etat == true)return State.errorCarteDejaRet;

        return State.CarteValide;
    }

}
