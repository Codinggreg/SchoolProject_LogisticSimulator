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

public class UsineIntegrationTest {
    private Usine usAile;
    private final int QTY_MIN=4;
    int int_prod;
    String composanteEntree;

    @Before
    public void Setup()
    {
        int_prod=10;
        composanteEntree = Metal.class.toString();

        usAile=new UsineAile(int_prod);
        usAile.AjouterTypeProduction(composanteEntree,QTY_MIN);

    }

    @Test
    public void EnleverDeInventaire_UsineAInventaireRetraitPlusGrandInventaire_InventaireEstZero(){
        int ajoutInventaire=4;
        int retraitInventaire=-5;
        usAile.gererAjout(composanteEntree,ajoutInventaire);
        usAile.gererAjout(composanteEntree,retraitInventaire);

        assertEquals(0,usAile.getQuantiteInventaire(composanteEntree));
    }
    @Test
    public void ProduireComposante_UsineAInventaireSuffisantPourProduire_InventaireDuneComposanteADiminueDeLaQuantiteRequise(){
        int ajoutInventaire=5;

        usAile.gererAjout(composanteEntree,ajoutInventaire);
        usAile.avancerTour(int_prod);

        usAile.extraireSortie();

        assertEquals(ajoutInventaire-QTY_MIN,usAile.getQuantiteInventaire(composanteEntree));
    }
    @Test
    public void PasDeProductionSiInventaireInsuffisant_UsineContientpeuInventaire_UsineNeProduitPas(){
        int ajoutInventaire=3;

        usAile.gererAjout(composanteEntree,ajoutInventaire);
        usAile.avancerTour(int_prod);

        assertNull(usAile.extraireSortie());
    }
    @Test
    public void usineNeProduitPasSiPasAssezDeTemps_UsineAInventaireSuffisantAuPremierTour_UsineNeProduitPas(){
        int avancementTour=1;

        usAile.gererAjout(composanteEntree,QTY_MIN);
        usAile.avancerTour(avancementTour);

        assertNull(usAile.extraireSortie());
    }
    @Test
    public void Production_UsineAuPremierTour_PasDeProduction()
    {

        usAile.gererAjout(composanteEntree,QTY_MIN);
        assertNull(usAile.extraireSortie());
    }
    @Test
    public void usineProduitSiInventaireSuffisant_UsineQuantiteSuffisanteBonneComposanteBonTour_UneComposante()
    {
        int ajoutInventaire=4;

        usAile.gererAjout(composanteEntree,QTY_MIN);
        usAile.avancerTour(int_prod);

        assertNotNull(usAile.extraireSortie());

    }
}
