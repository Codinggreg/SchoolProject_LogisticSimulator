package Main.Modele;

import Main.Modele.Usines.UsineAile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UsineTest {
    Usine usine;
    int intervalProd;
    @Before
    public void setUp() throws Exception {
        intervalProd=1;
        usine=new UsineAile(intervalProd,0,null);
    }

    @Test
    public void isBonTour_BonTour_Vrai() throws Exception {
        usine.set_intervalCourant(intervalProd);
        assertTrue(usine.isBonTour());
    }
    @Test
    public void isBonTour_MauvaisTour_Faux() throws Exception {
        usine.set_intervalCourant(1 + intervalProd);
        assertFalse(usine.isBonTour());
    }
    @Test
    public void isBonTour_TourZero_Faux() throws Exception {
        usine.set_intervalCourant(0);
        assertFalse(usine.isBonTour());
    }
}