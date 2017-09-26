package Modele;

import Modele.Composantes.Aile;
import Modele.Composantes.Avion;
import Modele.Composantes.Metal;
import Modele.Composantes.Moteur;
import Modele.Usines.UsineAile;
import Modele.Usines.UsineAvion;
import Modele.Usines.UsineMatiere;
import Modele.Usines.UsineMoteur;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class UsineTest {

    @Before
    public void Setup()
    {

    }
    @Test
    public void testUsineProduisentBonneComposantes()
    {
        Batiment usAile=new UsineAile();
        Batiment usAvion=new UsineAvion();
        Batiment usMoteur=new UsineMoteur();
        Batiment usMetal=new UsineMatiere();

        assertThat(usAile.getSortieProduction(),instanceOf(Aile.class));
        assertThat(usAvion.getSortieProduction(),instanceOf(Avion.class));
        assertThat(usMoteur.getSortieProduction(),instanceOf(Moteur.class));
        assertThat(usMetal.getSortieProduction(),instanceOf(Metal.class));
    }
}
