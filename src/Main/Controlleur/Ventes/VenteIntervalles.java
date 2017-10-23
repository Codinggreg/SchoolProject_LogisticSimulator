package Main.Controlleur.Ventes;

import Main.Controlleur.IVenteStrategie;

public class VenteIntervalles implements IVenteStrategie {
    private int _intervalCourant;
    private static final int INTERVAL_MAX =1000;

    public VenteIntervalles(){
        _intervalCourant=0;
    }

    @Override
    public boolean vendre() {
        _intervalCourant++;
        if(_intervalCourant>= INTERVAL_MAX)
        {
            _intervalCourant=0;
            return true;
        }
        return false;
    }
}
