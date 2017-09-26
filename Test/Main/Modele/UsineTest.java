package Main.Modele;

import Main.Modele.Composantes.Aile;
import Main.Modele.Composantes.Avion;
import Main.Modele.Composantes.Metal;
import Main.Modele.Composantes.Moteur;
import Main.Modele.Usines.UsineAile;
import Main.Modele.Usines.UsineAvion;
import Main.Modele.Usines.UsineMatiere;
import Main.Modele.Usines.UsineMoteur;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UsineTest {
    private Batiment usAile;
    private final int QTY_MIN=4;
    @Before
    public void Setup()
    {
        int int_prod=10;
        usAile=new UsineAile(QTY_MIN,int_prod);
    }
    @Test
    public void testUsineAjouterComposante(){
        int quantiteAjoutee=1;

        assertEquals(0,usAile.getQuantiteInventaire());

        usAile.ajouterInventaire(quantiteAjoutee);

        assertEquals(quantiteAjoutee,usAile.getQuantiteInventaire());
    }
    @Test
    public void testUsinesProduisentBonnesComposantes()
    {

        Batiment usAvion=new UsineAvion();
        Batiment usMoteur=new UsineMoteur();
        Batiment usMetal=new UsineMatiere();

        assertThat(usAile.extraireSortie(),instanceOf(Aile.class));
        assertThat(usAvion.extraireSortie(),instanceOf(Avion.class));
        assertThat(usMoteur.extraireSortie(),instanceOf(Moteur.class));
        assertThat(usMetal.extraireSortie(),instanceOf(Metal.class));
    }
    @Test
    public void extraireSortieVideInventaire(){
        int ajoutInventaire=3;

        usAile.ajouterInventaire(ajoutInventaire);
        usAile.extraireSortie();

        assertEquals(0,usine.getQuantiteInventaire());
    }
    @Test
    public void usineNeProduitPasSiInventaireInsuffisant(){
        int ajoutInventaire=3;
        usAile.ajouterInventaire(3);
        assertNull(usAile.extraireSortie());
    }
    @Test
    public void usineNeProduitPasSiPasAssezDeTemps(){
        usAile.ajouterInventaire(QTY_MIN);
        usAile.passerTour(1);
        assertNull(usAile.extraireSortie());
    }

    @Test
    public void usineProduitsiInventaireSuffisant()
    {
        int ajoutInventaire=4;

        usAile.passerTour(10);

        assertNotNull(usAile.extraireSortie());

    }
}
