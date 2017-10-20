package Main.Modele;

import Main.Modele.Usines.UsineAile;
import Main.Modele.Usines.UsineMatiere;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UsineTest {
    private Usine _usine;
    private int _intervalProd;
    @Before
    public void setUp() throws Exception {
        _intervalProd =89;
        _usine =new UsineMatiere(_intervalProd,0,null);
    }

    @Test
    public void isBonTour_BonTour_Vrai() throws Exception {
        _usine.set_intervalCourant(_intervalProd);
        assertTrue(_usine.isBonTour());
    }
    @Test
    public void isBonTour_MauvaisTour_Faux() throws Exception {
        _usine.set_intervalCourant(1 + _intervalProd);
        assertFalse(_usine.isBonTour());
    }
    @Test
    public void isBonTour_TourZero_Faux() throws Exception {
        _usine.set_intervalCourant(0);
        assertFalse(_usine.isBonTour());
    }
    @Test
    public void getStatut_TourDeProduction() throws Exception {
        _usine.avancerTour(_intervalProd);
        assertEquals(100, _usine.getStatut());
    }
    @Test
    public void getStatut_UnTiers() throws Exception {
        _usine.avancerTour(_intervalProd /3+1);
        assertTrue(_usine.getStatut()>=33 && _usine.getStatut()<66);
    }
    @Test
    public void getStatut_DeuxTiers() throws Exception {
        _usine.avancerTour((_intervalProd /3*2+1));
        assertTrue(_usine.getStatut()>=66);
    }
    @Test
    public void getStatut_tourZero() throws Exception {

        assertEquals(0, _usine.getStatut());
    }
    @Test
    public void getStatut_MoinsQueUnTiers() throws Exception {
        _usine.avancerTour(_intervalProd /3-1);
        assertTrue(_usine.getStatut()<33&& _usine.getStatut()>0);
    }
}