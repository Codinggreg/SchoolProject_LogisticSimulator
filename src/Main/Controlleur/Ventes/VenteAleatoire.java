package Main.Controlleur.Ventes;

import Main.Controlleur.IVenteStrategie;

import java.util.Random;

public class VenteAleatoire implements IVenteStrategie {
    private Random ran;
    public VenteAleatoire(){
        ran=new Random();
    }
    @Override
    public boolean vendre() {

        return ran.nextInt(10000)+1>=19995;
    }
}
