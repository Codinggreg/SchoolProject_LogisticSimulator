package Main.Modele.Usines;

import Main.Modele.Batiment;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class UsineComposanteTest {

    Batiment usAile,usAvion,usMoteur,usMetal;

    @Before
    public void setUp(){
        int interval=1;
        usAile= new UsineAile(1,0,null);
        usAvion= new UsineAvion(1,0,null);
        usMoteur= new UsineMoteur(1,0,null);
        usMetal= new UsineMatiere(1,0,null);
    }

    @Test
    public void getTypeSortie(){


        assertEquals("aile",usAile.getComposante().get_type());
        assertEquals("avion",usAvion.getComposante().get_type());
        assertEquals("moteur",usMoteur.getComposante().get_type());
        assertEquals("metal",usMetal.getComposante().get_type());
    }

}