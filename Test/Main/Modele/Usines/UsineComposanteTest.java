package Main.Modele.Usines;

import Main.Modele.Batiment;
import Main.Modele.Composantes.Aile;
import Main.Modele.Composantes.Avion;
import Main.Modele.Composantes.Metal;
import Main.Modele.Composantes.Moteur;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
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
        assertThat(usAile.getTypeSortie(),instanceOf(Aile.class));
        assertThat(usAvion.getTypeSortie(),instanceOf(Avion.class));
        assertThat(usMoteur.getTypeSortie(),instanceOf(Moteur.class));
        assertThat(usMetal.getTypeSortie(),instanceOf(Metal.class));
    }

}