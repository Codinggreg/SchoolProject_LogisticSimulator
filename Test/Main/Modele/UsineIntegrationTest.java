package Main.Modele;

import Main.Modele.Usines.UsineAile;
import Main.Modele.Usines.UsineMatiere;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UsineIntegrationTest {
    private Usine usAile;
    private final int QTY_MIN=4;
    int int_prod;
    Batiment dest;
    String composanteEntree;

    @Before
    public void Setup()
    {
        int_prod=10;
        composanteEntree = "metal";

        usAile=new UsineAile(int_prod,0,new Point(0,0));
        usAile.ajouterTypeProduction(composanteEntree,QTY_MIN);
        dest=new Entrepot(int_prod,1,new Point(0,0),2);
        usAile.set_destination(dest);
    }

    @Test
    public void avancerTour_CourantEtProdEgaux_DifferenceZero(){
        usAile.ajouterInventaire(composanteEntree,QTY_MIN);
        usAile.avancerTour(int_prod);
        assertEquals(0,usAile.getIntervalCourant()-usAile.getIntervalProd());
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

    @Test
    public void peutProduire_UsineMatiereProduitBonTour_SortieMetal(){
        Batiment usMatiere=new UsineMatiere(int_prod,0,null);
        usMatiere.avancerTour(int_prod);
        usMatiere.set_destination(dest);
        assertEquals("metal",usMatiere.extraireSortie().get_type());
    }
}
