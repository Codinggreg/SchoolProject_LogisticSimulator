package Main.Controlleur.Ventes;

import Main.Controlleur.IVenteStrategie;

import java.util.Random;

/**
 * Vente suivant une probabilité aléatoire
 */
public class VenteAleatoire implements IVenteStrategie {
    private Random ran;
    public VenteAleatoire(){
        ran=new Random();
    }

    /**
     *
     * @return si doit vendre ou non
     */
    @Override
    public boolean vendre() {
        return ran.nextInt(10000)+1>=9995;
    }
}
